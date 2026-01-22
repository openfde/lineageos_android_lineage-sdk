package android.openfde;

import android.app.Activity;
import java.lang.ref.WeakReference;


/**
 * proxy for  AppTaskControllerImpl
 * @hide
 */
public class AppTaskControllerProxy {

    private final WmShellAppTaskController mImpl;
    private SystemBarOperator mSystemBarOperator = null;
    private WmShellCaller mWmShellCaller = null;
    private AppTaskControllerProxy() {
        mImpl = new WmShellAppTaskController();
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

    public void back() {
        mImpl.back();
    }

    private void toggleStatusBarNavigationBar(boolean hide){
        mImpl.toggleStatusBarNavigationBar(hide);
    }

    private void callTaskOperation(int opCode){
        mImpl.callTaskOperation(opCode);
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

    public SystemBarOperator getSystemBarOperator(){
        if(mSystemBarOperator == null){
            mSystemBarOperator = new SystemBarOperator(){
                @Override
                public void toggleStatusBarNavigationBar(boolean hide){
                    AppTaskControllerProxy.this.toggleStatusBarNavigationBar(hide);
                }
            }
        }
        return mSystemBarOperator;
    }

    public WmShellCaller getSystemBarOperator(){
        if(mWmShellCaller == null){
            mWmShellCaller = new WmShellCaller(){
                @Override
                public void callTaskOperation(int opCode){
                    AppTaskControllerProxy.this.callTaskOperation(opCode);
                }
            }
        }
        return mWmShellCaller;
    }

}