/**
 * Represents one task managed by the Vani task list.
 *
 * A task starts as incomplete. Its completion state can be changed through
 * the mark methods, while the description remains the text entered by the
 * user.
 */
public class Task {
    /** The text that describes the work the user wants to remember. */
    protected String description;

    /** True when the task has been marked as completed. */
    protected boolean isDone;

    /**
     * Creates a new incomplete task with the supplied description.
     *
     * @param description the text entered for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbol used by the user interface to display this task's
     * completion state.
     *
     * @return {@code "X"} for a completed task, or a blank space otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /** Marks this task as completed. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markAsNotDone() {
        isDone = false;
    }
}
