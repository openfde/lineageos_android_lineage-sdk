package android.openfde;

import android.app.Activity;
import java.lang.ref.WeakReference;


/**
 * proxy for  AppTaskControllerImpl
 * @hide
 */
public class AppTaskControllerProxy {

    private final AppTaskControllerImpl mImpl;

    private AppTaskControllerProxy() {
        mImpl = new AppTaskControllerImpl();
    }

    public static AppTaskControllerProxy create() {
        return new AppTaskControllerProxy();
    }

    public static final int OPERATION_CLOSE = 0;
    public static final int OPERATION_BACK = 1;
    public static final int OPERATION_FULLSCREEN = 2;
    public static final int OPERATION_MINIMIZE = 3;
    public static final int OPERATION_MAXIMIZE = 4;

    public void initCustomCaption(WeakReference<Activity> activity, AppTaskStatusListener listener, boolean hideRawCaption) {
        mImpl.initCustomCaption(activity, listener, hideRawCaption);
    }

    public void enterOrExitFullscreen() {
        mImpl.enterOrExitFullscreen();
    }

    public void close() {
        mImpl.close();
    }

    public void minimize() {
        mImpl.minimize();
    }

    public void maximizeOrNot() {
        mImpl.maximizeOrNot();
    }

    public String getStatus() {
        return mImpl.getStatus();
    }

    public void cleanup(){
        mImpl.cleanup();
    }
}