package vani;

import vani.task.Task;

/**
 * Holds the meaning of a user command after parsing, without executing it.
 */
public class Command {
    /**
     * Identifies the operations supported by Vani and their command keywords.
     */
    public enum Type {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        private final String keyword;

        Type(String keyword) {
            this.keyword = keyword;
        }

        /**
         * Returns the command keyword, also used to name newly added task types.
         *
         * @return the keyword entered by the user
         */
        public String getKeyword() {
            return keyword;
        }
    }

    private final Type type;
    private final int taskNumber;
    private final Task task;

    /**
     * Creates a parsed command. Only Parser needs to construct these objects.
     *
     * @param type the operation to perform
     * @param taskNumber the task number for mark, unmark, or delete; otherwise 0
     * @param task the new task for an add command; otherwise null
     */
    Command(Type type, int taskNumber, Task task) {
        this.type = type;
        this.taskNumber = taskNumber;
        this.task = task;
    }

    /**
     * Returns the operation represented by this command.
     *
     * @return the command type
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the user-facing task number for a mark, unmark, or delete command.
     *
     * @return the task number, or 0 for commands that do not use one
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Returns the new task supplied by a todo, deadline, or event command.
     *
     * @return the task to add, or null for other commands
     */
    public Task getTask() {
        return task;
    }
}
