/*
 * Copyright (C) 2022 Tesla Android Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.wifi.tether;

import static android.net.ConnectivityManager.TETHERING_WIFI;

import android.net.ConnectivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import com.google.common.annotations.VisibleForTesting;

public class BootHotspotReceiver extends BroadcastReceiver {  
  
    private static final String TAG = "BootHotspotReceiver";  
  
    @VisibleForTesting  
    final ConnectivityManager.OnStartTetheringCallback mOnStartTetheringCallback =  
            new ConnectivityManager.OnStartTetheringCallback() {  
                @Override  
                public void onTetheringFailed() {  
                    super.onTetheringFailed();  
                }  
            };  
  
    @Override  
    public void onReceive(Context context, Intent intent) {  
  
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {  
            Log.d(TAG,"BootHotspotReceiver");  
  
            ConnectivityManager mConnectivityManager =  
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
            mConnectivityManager.startTethering(TETHERING_WIFI, true/* showProvisioningUi */,  
                    mOnStartTetheringCallback, new Handler(Looper.getMainLooper()));  
        }  
    }  
  
}  