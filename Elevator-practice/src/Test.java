import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		SimpleScheduler scheduler = new SimpleScheduler();
		int floors = 5;
		int numElevators = 1;
		//每层楼就一个request
		System.out.println("we only generate request for 1 time, so simulating steps > 2*(floors-1) didn't load any new request");
		List<Queue<Integer>> initial_requests = new ArrayList<>();
		for(int i=0; i<floors; i++) {
			Queue<Integer> cur_queue = new LinkedList<>();
			Random generator = new Random();
			int req = generator.nextInt(5)+1;
			// can't generate current floor
			while(req == i+1) {
				req = generator.nextInt(5)+1;
			}
			System.out.println("destination floor: "+req);
			cur_queue.offer(req);
			initial_requests.add(cur_queue);
		}
		Simulator mySimulator = 
				new Simulator(floors, numElevators, scheduler, initial_requests, 14);
		mySimulator.simulate(10);
		
	}

}
