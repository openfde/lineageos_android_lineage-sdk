package android.openfde;

import android.app.Activity;
import java.lang.ref.WeakReference;

/**
 * Proxy class for {@code AppTaskControllerImpl}.
 * <p>
 * This class serves as the primary controller for the Application Title Bar (Caption).
 * The methods provided here directly map to standard window controls:
 * </p>
 * <pre>
 * _________________________________________________________________
 * | [App Icon] Target Activity Name        [<]         [<>]                    [-]         [口]         [X] |
 * |_____________________________________(back)__(enterOrExitFullscreen)__(minimize)__(maximizeOrNot)__(close)
 * </pre>
 * <ul>
 * <li>(0) <b>close:</b> {@link #close()}</li>
 * <li>(1) <b>back:</b> {@link #back()}</li>
 * <li>(2) <b>Fullscreen:</b> {@link #enterOrExitFullscreen()}</li>
 * <li>(3) <b>minimize:</b> {@link #minimize()}</li>
 * <li>(4) <b>maxmized:</b> {@link #maximizeOrNot()} </li>
 * <li>(5) <b>relayout systemui caption:</b> {@link #callTaskOperation(5)}</li>
 * </ul>
 * * <p><b>Advanced Usage:</b> Developers can combine {@link #callTaskOperation(int)}
 * and {@link #toggleStatusBarNavigationBar(boolean)} to implement custom behaviors
 * or synchronized UI updates for the title bar.</p>
 */
public class AppTaskControllerProxy {

    private final WmShellAppTaskController mImpl;
    private SystemBarOperator mSystemBarOperator = null;
    private WmShellCaller mWmShellCaller = null;

    private AppTaskControllerProxy() {
        mImpl = new WmShellAppTaskController();
    }

    /**
     * Creates a new instance of {@link AppTaskControllerProxy}.
     * * @return A new proxy instance.
     */
    public static AppTaskControllerProxy create() {
        return new AppTaskControllerProxy();
    }

    /**
     * Initializes a custom caption (title bar) for the specified activity.
     * <p>
     * <strong>Note:</strong> This method must be called after the activity has executed
     * {@link Activity#setContentView(int)}.
     * </p>
     * * @param activity A {@link WeakReference} to the target Activity to prevent memory leaks.
     * @param listener Callback listener for task status updates.
     * @param hideRawCaption Set to {@code true} to hide the system's default caption bar.
     */
    public void initCustomCaption(WeakReference<Activity> activity, AppTaskStatusListener listener, boolean hideRawCaption) {
        mImpl.initCustomCaption(activity, listener, hideRawCaption);
    }

    /**
     * Toggles the task between fullscreen and windowed mode.
     */
    public void enterOrExitFullscreen() {
        mImpl.enterOrExitFullscreen();
    }

    /**
     * Closes the current application task.
     */
    public void closeTask() {
        mImpl.closeTask();
    }

    /**
     * Triggers a system-level back navigation event.
     */
    public void back() {
        mImpl.back();
    }

    /**
     * Toggles the visibility of the System Status Bar and Navigation Bar.
     * * @param hide {@code true} to hide both bars, {@code false} to show them.
     */
    public void toggleStatusBarNavigationBar(boolean hide){
        mImpl.toggleStatusBarNavigationBar(hide);
    }

    /**
     * Executes a specific task operation based on an operation code.
     * * @param opCode The specific operation code defined by the WmShell protocol.
     */
    public void callTaskOperation(int opCode){
        mImpl.callTaskOperation(opCode);
    }

    /**
     * Minimizes the current task window to the background/taskbar.
     */
    public void minimize() {
        mImpl.minimize();
    }

    /**
     * Toggles the maximization state of the task window.
     */
    public void maximizeOrNot() {
        mImpl.maximizeOrNot();
    }

    /**
     * Retrieves the current status of the task controller.
     * * @return A string representation (often JSON) of the current task status.
     */
    public String getStatus() {
        return mImpl.getStatus();
    }

    /**
     * Performs resource cleanup.
     * Should be called when the activity is destroyed to prevent leaks and clear references.
     */
    public void cleanup(){
        mImpl.cleanup();
    }

    /**
     * Returns an operator interface for managing system bars.
     * * @return An implementation of {@link SystemBarOperator}.
     */
    public SystemBarOperator getSystemBarOperator(){
        if(mSystemBarOperator == null){
            mSystemBarOperator = new SystemBarOperator(){
                @Override
                public void toggleStatusBarNavigationBar(boolean hide){
                    AppTaskControllerProxy.this.toggleStatusBarNavigationBar(hide);
                }
            };
        }
        return mSystemBarOperator;
    }

    /**
     * Returns a caller interface for WmShell operations.
     * * @return An implementation of {@link WmShellCaller}.
     */
    public WmShellCaller getmWmShellCaller(){
        if(mWmShellCaller == null){
            mWmShellCaller = new WmShellCaller(){
                @Override
                public void callTaskOperation(int opCode){
                    AppTaskControllerProxy.this.callTaskOperation(opCode);
                }
            };
        }
        return mWmShellCaller;
    }
}