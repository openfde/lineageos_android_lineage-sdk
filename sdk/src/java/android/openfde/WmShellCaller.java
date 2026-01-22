package android.openfde;


/**
 * @hide
 */
public interface WmShellCaller {

    public static final int OPERATION_CLOSE = 0;
    public static final int OPERATION_BACK = 1;
    public static final int OPERATION_FULLSCREEN = 2;
    public static final int OPERATION_MINIMIZE = 3;
    public static final int OPERATION_MAXIMIZE = 4;
    public static final int OPERATION_WINDOWDECORATION_RELAYOUT = 5;

    void callTaskOperation(int opCode);

}