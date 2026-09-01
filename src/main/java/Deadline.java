/**
 * Represents a deadline task that has a description and a due date.
 */

public class Deadline extends Task {

    private String by;

    /**
     * Creates a new incomplete deadline task with the supplied description and
     * due date.
     *
     * @param description the text entered for the task
     * @param by the due date entered for the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    // Returns information about this deadline task.
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }
    
}
