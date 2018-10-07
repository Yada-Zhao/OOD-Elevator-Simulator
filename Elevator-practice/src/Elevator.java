import java.util.Queue;

public class Elevator {
	public static final int DEFAULT_MAX_CAPACITY = 14;
	public static final int DEFAULT_MAX_FLOOR = 10;
	public static final int DEFAULT_INITIAL_LOCATION = 1;
	
	private final int maxCapacity;
	private final int maxFloor;
	private int load;
	private int location;
	private boolean isGoingUp;
	// all requests loaded into this elevator
	// every request is just a number-destination floor.
	private int[] requests;
	
	public Elevator(int maxCapacity, int maxFloor) {
		this.maxCapacity = maxCapacity;
		this.maxFloor = maxFloor;
		load = 0;
		location = 1;
		isGoingUp = false;
		requests = new int[maxFloor];
	}
	public Elevator() {
		this(DEFAULT_MAX_CAPACITY, DEFAULT_MAX_FLOOR);
	}
	
	public void print() {
		System.out.println("| location: "+this.location+" | load: "+ this.load +" | requests:");
		for(int i=0; i<requests.length; i++) {
			System.out.print(i+" floor: "+requests[i]+"|");
		}
		System.out.println();
	}
	public boolean isEmpty() {
		return load == 0;
	}
	
	public boolean isFull() {
		return load >= maxCapacity;
	}
	
	public int getLocation() {
		return this.location;
	}
	
	public void move() {
		if(isGoingUp) {
			this.location =  location+1 <= maxFloor ? location+1 : location;
		} else {
			this.location =  location-1 >= 1 ? location-1 : location;
		}
	}
	
	public boolean changeMoveDirection() {
		isGoingUp = !isGoingUp;
		return isGoingUp;
	}
	
	public int load(Queue<Integer> cur_queue) {
		int num_newLoad = cur_queue.size();
		if(load + num_newLoad > this.maxCapacity) {
			return 0;
		}
		for(int destination_floor : cur_queue) {
			requests[destination_floor-1] += 1;
		}
		load += num_newLoad;
		cur_queue.clear();
		return num_newLoad;
	}
	
	public int unload() {
		int num_unload = requests[this.location-1];
		load -= num_unload;
		requests[this.location-1] = 0;
		return num_unload;
	}
}
