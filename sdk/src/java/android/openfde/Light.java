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
package android.openfde;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

public class Light {
    private static final String TAG = "openfdelight";
    public static final String LIGHT_SERVICE = "openfdelight";

    /**
     * Unable to determine status, an error occured
     */
    public static final int ERROR_UNDEFINED = -1;

    private static ILight sService;
    private static Light sInstance;

    private Context mContext;

    private Light(Context context) {
        mContext = context == null ? null : context.getApplicationContext();
        sService = getService();
    }

    public static Light getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Light(context);
        }
        return sInstance;
    }

    /** @hide **/
    public static ILight getService() {
        if (sService != null) {
            return sService;
        }
        IBinder b = ServiceManager.getService(LIGHT_SERVICE);

        if (b == null) {
            Log.e(TAG, "null service. SAD!");
            return null;
        }

        sService = ILight.Stub.asInterface(b);
        return sService;
    }

	public int setBacklight(int brightness) {
        ILight service = getService();
        if (service == null) {
            return ERROR_UNDEFINED;
        }
        try {
            return service.setBacklight(brightness);
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return ERROR_UNDEFINED;
    }

	public int getBacklight() {
        ILight service = getService();
        if (service == null) {
            return ERROR_UNDEFINED;
        }
        try {
            return service.getBacklight();
        } catch (RemoteException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return ERROR_UNDEFINED;
    }
}
