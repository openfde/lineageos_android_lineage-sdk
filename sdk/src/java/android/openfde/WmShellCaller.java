package android.openfde;

/**
 * Interface for communicating with the Window Manager Shell (WmShell).
 * Provides a standardized way to trigger window-level operations via operation codes.
 */
public interface WmShellCaller {

    /** Operation code to close the current application task. */
    public static final int OPERATION_CLOSE = 0;

    /** Operation code to simulate a back button press. */
    public static final int OPERATION_BACK = 1;

    /** Operation code to toggle the task into fullscreen mode. */
    public static final int OPERATION_FULLSCREEN = 2;

    /** Operation code to minimize the task window. */
    public static final int OPERATION_MINIMIZE = 3;

    /** Operation code to maximize the task window. */
    public static final int OPERATION_MAXIMIZE = 4;

    /** Operation code to request a relayout of the window decoration (caption/borders). */
    public static final int OPERATION_WINDOWDECORATION_RELAYOUT = 5;

    /**
     * Executes a specific task operation.
     * * @param opCode The identifier of the operation to perform.
     * Should be one of the {@code OPERATION_*} constants defined in this interface.
     */
    void callTaskOperation(int opCode);
}