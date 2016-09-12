package course.labs.activitylab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ActivityOne extends Activity {

    // string for logcat documentation
    private final static String TAG = "Lab-ActivityOne";

    // shared pref
    public  static final String PREF_COUNTERS = "Counters";

    // State saving
    static final String STATE_ONCREATE = "onCreate";
    static final String STATE_ONSTART = "onStart";
    static final String STATE_ONRESUME = "onResume";
    static final String STATE_ONPAUSE = "onPause";
    static final String STATE_ONSTOP = "onStop";
    static final String STATE_ONDESTROY = "onDestroy";
    static final String STATE_ONRESTART = "onRestart";

    // lifecycle counts
    private int onCreate = 0;
    private int onStart = 0;
    private int onResume = 0;
    private int onPause = 0;
    private int onStop = 0;
    private int onDestroy = 0;
    private int onRestart = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        // Restore file first
        SharedPreferences settings = getSharedPreferences(PREF_COUNTERS, 0);
        onCreate = settings.getInt("onCreate", 0);
        onStart = settings.getInt("onStart", 0);
        onResume = settings.getInt("onResume", 0);
        onPause = settings.getInt("onPause", 0);
        onStop = settings.getInt("onStop", 0);
        onDestroy = settings.getInt("onDestroy", 0);
        onRestart = settings.getInt("onRestart", 0);

        // Restore instance second
        if(savedInstanceState !=null){
            onCreate = savedInstanceState.getInt(STATE_ONCREATE);
            onStart = savedInstanceState.getInt(STATE_ONSTART);
            onResume = savedInstanceState.getInt(STATE_ONRESUME);
            onPause = savedInstanceState.getInt(STATE_ONPAUSE);
            onStop = savedInstanceState.getInt(STATE_ONSTOP);
            onDestroy = savedInstanceState.getInt(STATE_ONDESTROY);
            onRestart = savedInstanceState.getInt(STATE_ONRESTART);
        }

        //Log cat print out
        Log.i(TAG, "onCreate called");

        //Increment
        onCreate++;

        //Update view
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_one, menu);
        return true;
    }

    // lifecycle callback overrides

    @Override
    public void onStart(){
        super.onStart();

        //Log cat print out
        Log.i(TAG, "onStart called");

        //Increment
        onStart++;

        //Update view
        updateView();
    }

    @Override
    public void onResume(){
        super.onResume();

        //Log cat print out
        Log.i(TAG, "onResume called");

        //Increment
        onResume++;

        //Update view
        updateView();
    }

    @Override
    public void onPause(){
        super.onPause();

        //Log cat print out
        Log.i(TAG, "onPause called");

        //Increment
        onPause++;

        //Update view
        updateView();
    }

    @Override
    public void onStop(){
        super.onStop();

        //Log cat print out
        Log.i(TAG, "onStop called");

        //Increment
        onStop++;

        //Update view
        TextView value = (TextView)findViewById(R.id.stop);
        String text = value.getText().toString().substring(0, value.getText().toString().indexOf(':'));
        value.setText(text + ": " + onStop);

        //Creating editor and saving counters to pref
        SharedPreferences settings = getSharedPreferences(PREF_COUNTERS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("onCreate", onCreate);
        editor.putInt("onStart", onStart);
        editor.putInt("onResume", onResume);
        editor.putInt("onPause", onPause);
        editor.putInt("onStop", onStop);
        editor.putInt("onDestroy", onDestroy);
        editor.putInt("onRestart", onRestart);

        // Commit the edits!
        editor.commit();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        //Log cat print out
        Log.i(TAG, "onDestroy called");

        //Increment
        onDestroy++;

        //Update view
        updateView();
    }

    @Override
    public void onRestart(){
        super.onRestart();

        //Log cat print out
        Log.i(TAG, "onRestart called");

        //Increment
        onRestart++;

        //Update view
        updateView();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        //Save counters
        savedInstanceState.putInt(STATE_ONCREATE, onCreate);
        savedInstanceState.putInt(STATE_ONSTART, onStart);
        savedInstanceState.putInt(STATE_ONRESUME, onResume);
        savedInstanceState.putInt(STATE_ONPAUSE, onPause);
        savedInstanceState.putInt(STATE_ONSTOP, onStop);
        savedInstanceState.putInt(STATE_ONDESTROY, onDestroy);
        savedInstanceState.putInt(STATE_ONRESTART, onRestart);

        //Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void launchActivityTwo(View view) {
        //Launching activity
        Intent activity2 = new Intent(getApplicationContext(), ActivityTwo.class);
        startActivity(activity2);
    }

    public void updateView(){
        //Get lines
        TextView on_create = (TextView)findViewById(R.id.create);
        TextView on_start = (TextView)findViewById(R.id.start);
        TextView on_resume = (TextView)findViewById(R.id.resume);
        TextView on_pause = (TextView)findViewById(R.id.pause);
        TextView on_stop = (TextView)findViewById(R.id.stop);
        TextView on_destroy = (TextView)findViewById(R.id.destroy);
        TextView on_restart = (TextView)findViewById(R.id.restart);

        //Set lines
        on_create.setText(on_create.getText().toString().substring(0, on_create.getText().toString().indexOf(':')) + ": " + onCreate);
        on_start.setText(on_start.getText().toString().substring(0, on_start.getText().toString().indexOf(':')) + ": " + onStart);
        on_resume.setText(on_resume.getText().toString().substring(0, on_resume.getText().toString().indexOf(':')) + ": " + onResume);
        on_pause.setText(on_pause.getText().toString().substring(0, on_pause.getText().toString().indexOf(':')) + ": " + onPause);
        on_stop.setText(on_stop.getText().toString().substring(0, on_stop.getText().toString().indexOf(':')) + ": " + onStop);
        on_destroy.setText(on_destroy.getText().toString().substring(0, on_destroy.getText().toString().indexOf(':')) + ": " + onCreate);
        on_restart.setText(on_restart.getText().toString().substring(0, on_restart.getText().toString().indexOf(':')) + ": " + onRestart);
    }
}
