import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Simulator {
	private final int floors;
	private List<Queue<Integer>> requests;
	private List<Elevator> elevators;
	private Scheduler scheduler;
	
	public Simulator(int floors, int numElevators, Scheduler scheduler, 
			List<Queue<Integer>> initial_requests, int elevatorCapacity) {
		this.floors = floors;
		this.scheduler = scheduler;
		this.requests = initial_requests;
		this.elevators = new ArrayList<>();
		for(int i=0; i<numElevators; i++) {
			Elevator ele = new Elevator(elevatorCapacity, floors);
			elevators.add(ele);
		}
	}
	// didn't provide this functionality
	private void generateNewRequests() {
		
	}
	
	private void schedule() {
		this.scheduler.schedule(requests, elevators, floors);
	}
	// 什么动作?
	private void elevatorOp() {
		for(Elevator e : elevators) {
			if(!e.isEmpty()) {
				e.unload();
			}
			if(!e.isFull()) {
				int location = e.getLocation();
				e.load(requests.get(location-1));
			}
			e.move();
			e.print();
		}
	}
	public void simulate(int steps) throws InterruptedException {
		System.out.println("before first moving step: elevator status: ");
		for(Elevator e: elevators) {
			e.print();
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Here every status means just finish the previous load & unload, didn't doing in current floor yet");
		System.out.println("begin to move: ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int i=0; i<steps; i++) {
			generateNewRequests();
			schedule();
			elevatorOp();
			Thread.sleep(100);
		}
	}
	
}
