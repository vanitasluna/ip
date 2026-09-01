public class Todo extends Task {
    /**
     * Creates a new incomplete todo task with the supplied description.
     *
     * @param description the text entered for the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription();
    }
    
}
