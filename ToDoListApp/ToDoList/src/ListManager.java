import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// All the essential methods for the program
public class ListManager {
	static Scanner input = new Scanner(System.in);
	
	public static void printUI() {
		System.out.println("1. Add a Task \n2. Remove Task \n3. Edit Task \n4. See To-Do List");
		System.out.print("Choice: ");
	}
	
	// Get a number from the user
	private static int getIntInput() {
		int choice;
		
		try {
			choice = input.nextInt();
			return choice;
		} catch (InputMismatchException e) {
			return 0;
		} catch (NoSuchElementException e) {
			return 0;
		}
	}
	
	// Get a String from the user
	public static String getStringInput() {
		return input.nextLine();
	}
	
	public static boolean parseChoice() {
		int choice = getIntInput();
		input.nextLine();
		switch (choice) {
			case 1:
				addTask();
				return true;
			case 2:
				removeTask();
				return true;
			case 3:
				editTask();
				return true;
			case 4:
				displayList();
				return true;
			case 5:
				exit();
			default:
				System.out.print("This is not an option!");
				return false;
		}
	}
	
	public static void addTask() {	
		Task task;
		
		boolean done = false;
		do {
			System.out.println();
			System.out.println("What the task's name? ");
			String name = getStringInput();
			System.out.println("What the task's description? ");
			String description = getStringInput();
			System.out.println("Is the task completed? (true or false [default])");
			
			boolean completed = Boolean.parseBoolean(getStringInput());
			task = new Task(name, description, completed);
			TaskList.addTask(task);
			System.out.println("Want to add another task? (true or false [default])");
			done = !Boolean.parseBoolean(getStringInput());
			
		} while (!done);
		
		System.out.println("This is the last task you added:\n"+task);
	}
	
	public static void removeTask() {
		Task removedTask = null;
		
		int choice;
		boolean done = false;
		do {
			int count = 0;
			for (Task task : TaskList.getTasks()) {
				count++;
				System.out.println(count + ""+ task);
			}
			
			String display = String.format("What task would you like to remove? %d-%d", 1,
					TaskList.getNumberOfTasks());
			System.out.println(display);
			choice = getIntInput() - 1;
			// Check if equals -1 because an invalid input returns 0 then subtract 1
			if (choice == -1) {
				System.out.println("Invalid choice");
				continue;
			}
			removedTask = TaskList.getTasks().get(choice);
			TaskList.removeTask(choice);
			System.out.println("Want to remove another task? (true or false [default])");
			done = !Boolean.parseBoolean(getStringInput());
		} while (!done);
		
		System.out.println();
		System.out.println("This is the last task you removed:\n"+removedTask);
	}
	
	public static void editTask() {	
		Task task = null;
		
		boolean done = false;
		do {
			String display = String.format("What task would you like to edit? %d-%d", 1,
					TaskList.getNumberOfTasks());
			System.out.println(display);
			
			int choice = getIntInput() - 1;
			input.nextLine();
			// Check if equals -1 because an invalid input returns 0 then subtract 1
			if (choice == -1 || choice > TaskList.getNumberOfTasks()) {
				System.out.println("Invalid choice");
				continue;
			}
			task = TaskList.getTasks().get(choice);
			
			System.out.println();
			System.out.println("What the new task's name? (Leave empty if same)");
			String name = getStringInput();		
			name = name.equals("") ? name = task.getName() : name;
			task.setName(name);
			
			System.out.println("What the task's description? (Leave empty if same)");
			String description = getStringInput();
			description = description.equals("") ? description = task.getDescription() : 
				description;
			task.setDescription(description);
			System.out.println("Is the task completed? (true or false [default])");
			boolean completed = Boolean.parseBoolean(getStringInput());
			task.setCompleted(completed);
			
			System.out.println("Want to edit another task? (true or false [default])");
			done = !Boolean.parseBoolean(getStringInput());
			
		} while (!done);
		
		System.out.println("This is the last task you editted:\n"+task);
	}
	
	public static void displayList() {
		System.out.println();
		System.out.println(TaskList.displayTasks());
	}
	
	public static void exit() {
		System.out.println("Exiting the To-Do List program. Have a great day!");
		System.exit(0);
	}
}
