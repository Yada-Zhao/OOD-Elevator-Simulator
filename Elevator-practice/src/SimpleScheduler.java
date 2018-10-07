import java.util.List;
import java.util.Queue;

public class SimpleScheduler implements Scheduler {

	@Override
	public void schedule(List<Queue<Integer>> requests, List<Elevator> elevators, int floors) {
		for(Elevator e : elevators) {
			if(e.getLocation() == 1 || e.getLocation() == floors) {
				e.changeMoveDirection();
			}
		}
	}
}
