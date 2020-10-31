package com.example.testandrun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testandrun.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;
       private int mCount=0;
    private int mColor;
    private final String COUNT_KEY = "count";

    private final String COLOR_KEY = "color";
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.example.android.testandrun";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());





        mColor = ContextCompat.getColor(this, R.color.default_back);
        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);


        mCount = mPreferences.getInt(COUNT_KEY, 0);
        b.textView2.setText(String.format("%s", mCount));
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        b.textView2.setBackgroundColor(mColor);







    }
    @Override
    protected void onPause() {
        super.onPause();
        // creating a shared preferences editor
        SharedPreferences.Editor prefEditor = mPreferences.edit();

        //putting the key value pairs
        prefEditor.putInt(COUNT_KEY, mCount);
        prefEditor.putInt(COLOR_KEY, mColor);

        //applying the changes
        prefEditor.apply();
    }





        public void changeBackground(View view) {
            switch (view.getId()) {
                case R.id.button_black:
                    mColor = getResources().getColor(R.color.black);
                    break;
                case R.id.button_purple:
                    mColor = getResources().getColor(R.color.purple_200);
                    break;
                case R.id.button_pink:
                    mColor = getResources().getColor(R.color.pink);
                    break;
                case R.id.button_blue:
                    mColor = getResources().getColor(R.color.blue);
                    break;
                default:
                    mColor = getResources().getColor(R.color.default_back);

            }

            b.textView2.setBackgroundColor(mColor);
        }


    public void countUp(View view) {
        ++mCount;
        b.textView2.setText(String.format("%s", mCount));
    }


    public void reset(View view) {
        // Reset count
        mCount = 0;
        b.textView2.setText(String.format("%s", mCount));

        // Reset color
        mColor = ContextCompat.getColor(this,
                R.color.default_back);
        b.textView2.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putInt(COUNT_KEY, mCount);
//        outState.putInt(COLOR_KEY, mColor);
//    }

}