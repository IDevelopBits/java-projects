import java.util.ArrayList;

public class TaskList {
	private static ArrayList<Task> tasks = new ArrayList<>(); 
	
	private TaskList() {}
	
	public static ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public static int getNumberOfTasks() {
		return tasks.size();
	}
	
	public static void addTask(Task task) {
		tasks.add(task);
	}
	
	public static void removeTask(int choice) {
		tasks.remove(choice);
	}
	
	public static String displayTasks() {
		String list = "";
		for (Task task : TaskList.getTasks()) {
			list += task+"\n\n";
		}	
		return list.strip();
	}
}
