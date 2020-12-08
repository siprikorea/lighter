package com.siprikorea.lighter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    final int mChange = 10;

    /**
     * Set brightness
     * @param brightness brightness
     */
    void setBrightness(int brightness) {
        brightness = brightness < 0 ? 0 : brightness;
        brightness = brightness > 100 ? 100 : brightness;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = brightness / (float) 100;
        getWindow().setAttributes(params);
    }

    /**
     * Get brightness
     * @return brightness
     */
    int getBrightness() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        return (int) (params.screenBrightness * 100);
    }

    /**
     * Save brightness
     */
    void saveBrightness() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("Brightness", getBrightness());
        editor.apply();
    }

    /**
     * Load brightness
     */
    void loadBrightness() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        setBrightness(sharedPref.getInt("Brightness", 50));
    }

    /**
     * Update brightness
     */
    void updateBrightness() {
        TextView textBrightness = findViewById(R.id.brightness);
        textBrightness.setText(getBrightness() + " %");
    }

    /**
     * On create
     * @param savedInstanceState Saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadBrightness();
        updateBrightness();

        View view = findViewById(R.id.content);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {

            @Override
            public void onSwipeRight() {
                Log.d(TAG, "Swipe Right");
                setBrightness(getBrightness() + mChange);
                updateBrightness();
            }

            @Override
            public void onSwipeLeft() {
                Log.d(TAG, "Swipe Left");
                setBrightness(getBrightness() - mChange);
                updateBrightness();
            }

            @Override
            public void onSwipeTop() {
                Log.d(TAG, "Swipe Top");
                setBrightness(getBrightness() + mChange);
                updateBrightness();
            }

            @Override
            public void onSwipeBottom() {
                Log.d(TAG, "Swipe Bottom");
                setBrightness(getBrightness() - mChange);
                updateBrightness();
            }
        });
    }

    /**
     * On destroy
     */
    @Override
    protected void onDestroy() {
        saveBrightness();
        super.onDestroy();
    }
}