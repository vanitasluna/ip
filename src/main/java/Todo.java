/**
 * Represents a todo task that has only a description.
 */

public class Todo extends Task {

    /** Creates a new incomplete todo task with the supplied description.
     * 
     * @param description the text entered for the task
     */
    public Todo(String description) {
        super(description);
    }

    // Returns information about this todo task.
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
    
}
