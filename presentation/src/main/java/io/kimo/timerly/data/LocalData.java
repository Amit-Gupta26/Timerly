package io.kimo.timerly.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.kimo.timerly.mvp.model.TimerModel;

/**
 * Created by Kimo on 7/21/15.
 */
public class LocalData {
    public static final String TAG = LocalData.class.getSimpleName();
    public static final int PRIVATE_MODE = 0;

    public static String LOCAL_TIMERS = TAG + ".LOCAL_TIMERS";

    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public LocalData(Context context) {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(TAG, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public List<TimerModel> getLocalTimers() {
        Type listOfTimers = new TypeToken<List<TimerModel>>(){}.getType();

        String serializedTimers = sharedPreferences.getString(LOCAL_TIMERS, "");

        if(TextUtils.isEmpty(serializedTimers)) {
            return new ArrayList<>();
        } else {
            return new Gson().fromJson(serializedTimers, listOfTimers);
        }
    }

    public void setLocalTimers(List<TimerModel> timers) {
        Type listOfTimers = new TypeToken<List<TimerModel>>(){}.getType();
        String serializedTimers = new Gson().toJson(timers, listOfTimers);

        editor.putString(LOCAL_TIMERS, serializedTimers);
        editor.commit();
    }
}
