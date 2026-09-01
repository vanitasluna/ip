/**
 * Represents one task managed by the Vani task list.
 * 
 * A task starts as incomplete. Its completion state can be changed through
 * the mark methods, while the description remains the text entered by the
 * user.
 */
public class Task {

    private String description;
    private boolean isDone;

    /** Creates a new incomplete task with the supplied description.
     * 
     * @param description the text entered for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the symbol used by the user interface to display this task's
     * completion state.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    // Returns information about this task.
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
