package android.openfde;

public interface AppTaskStatusListener {

    public static final int WINDOWING_MODE_UNDEFINED =     0;   // {@link WindowConfiguration#WINDOWING_MODE_UNDEFINED} windowing
    public static final int WINDOWING_MODE_FULLSCREEN =    1;   // {@link WindowConfiguration#WINDOWING_MODE_FULLSCREEN} windowing
    public static final int WINDOWING_MODE_FREEFORM =      5;   // {@link WindowConfiguration#WINDOWING_MODE_FREEFORM} windowing

    void onStatusChanged(int windowingMode, boolean isSystemBarVisible);

    default String getStatus(int windowingMode, boolean isSystemBarVisible){
        if(windowingMode == WINDOWING_MODE_FULLSCREEN
         && isSystemBarVisible){
            return "fullscreen just maximize";
        } else if( windowingMode == WINDOWING_MODE_FREEFORM ){
            return "freeform";
        } else if( windowingMode == WINDOWING_MODE_FULLSCREEN ){
            return "real fullscreen";
        }
        return "undefined";
    }

    default String getListenerId() {
        return getClass().getSimpleName() + "@" + hashCode();
    }
}