public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates a new incomplete event task with the supplied description, start
     * date, and end date.
     *
     * @param description the text entered for the task
     * @param from the start date entered for the task
     * @param to the end date entered for the task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (from: " + from + " to: " + to + ")";
    }
    
}
