package com.petermorits.moritswille.metronomeapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {
    MediaPlayer click = MediaPlayer.create(this, R.raw.metronome1a);
    boolean playOrNot = false;
    int bpm;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                TextView text1 = (TextView) findViewById(R.id.text1);
                EditText bpmText = (EditText) findViewById(R.id.bpmText);
                bpm = Integer.parseInt(bpmText.getText().toString());
                if (playOrNot == true) {
                    text1.setText("Stop..");
                    playOrNot = false;
                } else {
                    metroPlay();
                    text1.setText("GO!");
                }
            }
        });
    }
    void metroPlay()
    {
        new CountDownTimer(bpm / 60000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                click.start();
            }
        }.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
