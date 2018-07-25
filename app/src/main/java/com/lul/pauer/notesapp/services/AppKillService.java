package com.lul.pauer.notesapp.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lul.pauer.notesapp.models.SavePreferences;

public class AppKillService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.lul.pauer.notesapp",MODE_PRIVATE);
        SavePreferences savePreferences = new SavePreferences(sharedPreferences);
        savePreferences.saveNoteList();

    }
}
