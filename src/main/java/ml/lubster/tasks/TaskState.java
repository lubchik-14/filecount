package ml.lubster.tasks;

import java.math.BigInteger;

/**
 * Describes a state of the finished task.
 */
public class TaskState {
    /**
     * String uri to start search from.
     */
    private String path;
    /**
     * Count of files that were found by the end of search.
     */
    private BigInteger fileCount;
    /**
     * The present state of the task.
     */
    private State state;

    public enum State {
        COMPLETE,
        CANCELLED,
        INVALID,
        NO_ACCESS,
        NONE
    }

    /**
     * Creates an instance of the class.
     *
     * @param path String uri
     * @param fileCount the count of files that were found in {@link TaskState#path} and all its subdirectories
     * @param state The state of the task.
     */
    public TaskState(String path, BigInteger fileCount, State state) {
        this.path = path;
        this.fileCount = fileCount;
        this.state = state;
    }

    public String getPath() {
        return this.path;
    }

    public BigInteger getFileCount() {
        return this.fileCount;
    }

    /**
     * Increments the count of found files.
     */
    public void incrementFileCount() {
        this.fileCount = fileCount.add(BigInteger.ONE);
    }

    /**
     * Returns the present state of the task.
     *
     * @return the present state of the task.
     */
    public State getTaskState() {
        return state;
    }

    /**
     * Sets the flag that the task was interrupted.
     */
    public void setState(State newState) {
        this.state = newState;
    }

}


