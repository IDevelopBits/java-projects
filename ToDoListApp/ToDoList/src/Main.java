public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to your To-Do List! \nHere are your options: ");
        
        // Filler tasks to test the program;
        Task task1 = new Task("Grocery Shopping", "Buy fruits and vegetables", false);
        Task task2 = new Task("Finish Project", "Complete the final report for the project", false);
        Task task3 = new Task("Gym", "Attend yoga class at 6 PM", true);
        
        TaskList.addTask(task1);
        TaskList.addTask(task2);
        TaskList.addTask(task3);

        boolean continueProgram = true;
        
        while (continueProgram) {

            // Display the main menu
            ListManager.printUI();

            // Execute the user's choice and return to the main menu
            boolean validChoice = ListManager.parseChoice();

            // After a method is finished, it waits for user input to go back to the main menu
            if (validChoice) {
            	System.out.println("\nPress any key to return to the main menu");
            	ListManager.getStringInput();
            }
        }
    }
}
