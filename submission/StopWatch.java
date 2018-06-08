/**
 * From Java Concepts Early Objects 8th Edition
 * @author Cay Horstmann
 *
 * This program can be used to time the execution of a program using the system time.
 *
 */
public class StopWatch {
    private long elapsedTime;
    private long startTime;
    private boolean isRunning;

    /**
     * Constructs a StopWatch.
     */
    public StopWatch() {
        reset();
    }

    /**
     * Resets the timer to 0.
     */
    public void reset() {
        elapsedTime = 0;
        isRunning = false;
    }

    /**
     * Starts the stop watch if it isn't already started.
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Stops the stop watch if it is running.
     */
    public void stop() {
        if (isRunning) {
            isRunning = false;
            long endTime = System.currentTimeMillis();
            elapsedTime = elapsedTime + endTime - startTime;
        }
    }

    /**
     * Get's the time since the stop watch started in milliseconds. This does not stop the stop watch.
     *
     * @return the number of milliseconds since the timer began.
     */
    public long getElapsedTime() {
        if (isRunning) {
            long endTime = System.currentTimeMillis();
            return elapsedTime + endTime - startTime;
        }
        return elapsedTime;
    }


}
