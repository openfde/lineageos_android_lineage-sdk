/**
 * Copyright (C) 2021 The WayDroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lineageos.waydroid;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

import lineageos.app.LineageContextConstants;

public class Hardware {
    private static final String TAG = "WayDroidHardware";

    /**
     * Unable to determine status, an error occured
     */
    public static final int ERROR_UNDEFINED = -1;

    private static IHardware sService;
    private static Hardware sInstance;

    private Context mContext;

    private Hardware(Context context) {
        Context appContext = context.getApplicationContext();
        mContext = appContext == null ? context : appContext;
        sService = getService();
        if (sService == null) {
            throw new RuntimeException("Unable to get WayDroidService. The service" +
                    " either crashed, was not started, or the interface has been called to early" +
                    " in SystemServer init");
                }
    }

    /**
     * Get or create an instance of the {@link lineageos.waydroid.Hardware}
     *
     * @param context Used to get the service
     * @return {@link Hardware}
     */
    public static Hardware getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Hardware(context);
        }
        return sInstance;
    }

    /** @hide **/
    public static IHardware getService() {
        if (sService != null) {
            return sService;
        }
        IBinder b = ServiceManager.getService(LineageContextConstants.WAYDROID_HARDWARE_SERVICE);

        if (b == null) {
            Log.e(TAG, "null service. SAD!");
            return null;
        }

        sService = IHardware.Stub.asInterface(b);
        return sService;
    }

    /** @hide **/
    public int enableNFC(boolean enable) {
        if (sService == null) {
            return ERROR_UNDEFINED;
        }
        try {
            return sService.enableNFC(enable);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return ERROR_UNDEFINED;
    }

    public int enableBluetooth(boolean enable) {
        if (sService == null) {
            return ERROR_UNDEFINED;
        }
        try {
            return sService.enableBluetooth(enable);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return ERROR_UNDEFINED;
    }

    public void suspend() {
        if (sService == null) {
            return;
        }
        try {
            sService.suspend();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return;
    }

    public void reboot() {
        if (sService == null) {
            return;
        }
        try {
            sService.reboot();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return;
    }

    public void upgrade(String system_zip, int system_time, String vendor_zip, int vendor_time) {
        if (sService == null) {
            return;
        }
        try {
            sService.upgrade(system_zip, system_time, vendor_zip, vendor_time);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return;
    }
}
