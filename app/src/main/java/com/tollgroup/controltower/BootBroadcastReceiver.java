package com.tollgroup.controltower;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by pong on 2017/4/3.
 * reciver the BootBroadcast, then start th service to listen the realtime DB
 */

public class BootBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            //Intent.ACTION_BOOT_COMPLETED == android.intent.action.BOOT_COMPLETED

            Intent intent1 = new Intent(context , MainActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
            //執行一個Activity

            Intent intent2 = new Intent(context , AlertService.class);
            context.startService(intent2);
            //執行一個Service
        }
    }
}