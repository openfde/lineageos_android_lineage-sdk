package android.openfde;

/**
 * Listener interface for monitoring changes in application task status and windowing configuration.
 * <p>
 * Implementations can track transitions between window modes (e.g., Freeform vs. Fullscreen)
 * and respond to changes in system UI visibility.
 * </p>
 * @hide
 */
public interface AppTaskStatusListener {

    /** Indicates the windowing mode is not explicitly defined. */
    public static final int WINDOWING_MODE_UNDEFINED = 0;

    /** Indicates the task is in standard fullscreen mode. */
    public static final int WINDOWING_MODE_FULLSCREEN = 1;

    /** Indicates the task is in a floating, resizable freeform window. */
    public static final int WINDOWING_MODE_FREEFORM = 5;

    /**
     * Called when the task windowing mode or system bar visibility changes.
     * * @param windowingMode The current windowing mode (e.g., {@link #WINDOWING_MODE_FULLSCREEN}).
     * @param isSystemBarVisible {@code true} if the Status/Navigation bars are currently shown.
     */
    void onStatusChanged(int windowingMode, boolean isSystemBarVisible);

    /**
     * Helper method to retrieve a human-readable description of the current task state.
     * * @param windowingMode The current windowing mode.
     * @param isSystemBarVisible The current system bar visibility state.
     * @return A string representing the UI state, such as "freeform" or "real fullscreen".
     */
    default String getStatus(int windowingMode, boolean isSystemBarVisible){
        if(windowingMode == WINDOWING_MODE_FULLSCREEN && isSystemBarVisible){
            // Fullscreen mode but bars are visible, typically behaving like a maximized window
            return "fullscreen just maximize";
        } else if( windowingMode == WINDOWING_MODE_FREEFORM ){
            // Standard windowed/resizable mode
            return "freeform";
        } else if( windowingMode == WINDOWING_MODE_FULLSCREEN ){
            // Fullscreen mode with bars hidden
            return "real fullscreen";
        }
        return "undefined";
    }

    /**
     * Generates a unique identifier for the listener instance.
     * Useful for debugging and logging purposes.
     * * @return A unique string ID based on the class name and hash code.
     */
    default String getListenerId() {
        return getClass().getSimpleName() + "@" + hashCode();
    }
}