
public class Task {
	private String name;
	private String description;
	private boolean isCompleted;
	
	public Task(String name, String description, boolean completed) {
		this.name = name;
		this.description = description;
		this.isCompleted = completed;
	}
	
	public Task(String name, String description) {
		this(name, description, false);
	}
	
	public Task(String name) {
		this(name, "", false);
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description.toString();
	}
	
	public boolean getCompleted() {
		return isCompleted;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCompleted(boolean completed) {
		this.isCompleted = completed;
	}
	
	public String toString() {
		return String.format("Name: %s \nDescription: %s \nCompleted: %B", name, description, isCompleted);
	}
	
}
