package android.openfde;

/**
 * Interface for controlling the visibility of system-level UI components.
 * This is primarily used to manage the System Bar and Navigation Bar state for the current task.
 */
public interface SystemBarOperator {

    /**
     * Toggles the visibility of both the Status Bar and the Navigation Bar.
     * * @param hide {@code true} to hide the system bars for an immersive experience;
     * {@code false} to show them.
     */
    void toggleStatusBarNavigationBar(boolean hide);
}