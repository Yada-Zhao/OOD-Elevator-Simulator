import java.util.List;
import java.util.Queue;

public interface Scheduler {
	// Scheduler change a 
	public void schedule(List<Queue<Integer>> requests, List<Elevator> elevators, int floors);
}
