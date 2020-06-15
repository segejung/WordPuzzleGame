package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_SETTINGS = 1;

    private int numberOfMinutes = 3;
    private int boardSize = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.playButton);
        Button settingsButton = findViewById(R.id.settingsButton);
        Button view_statistics = (Button)findViewById(R.id.statisticsButton);

        // If play button clicked then navigate to Word Game UI and pass in
        // data from settings
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), WordGame.class);
                startIntent.putExtra("minutes", numberOfMinutes);
                startIntent.putExtra("boardSize", boardSize);
                startActivity(startIntent);
            }
        });

        // If settings clicked then navigate to Settings UI and retrieve data from settings
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivityForResult(intent, REQUEST_CODE_SETTINGS);
            }
        });

        //If Statistics clicked then navigate to Statistics UI and show the stats results
        view_statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent startIntent = new Intent(getApplicationContext(), Statistics.class);
                //TEST: the following is for test!!!!!
                startIntent.putExtra("edu.gatech.seclass.gameapp.SOMETHING", "2HELLO WORLD!!!!" );
                startActivity(startIntent);
            }
        } );

    }

    // This method is called when retrieving data from a child UI (such as Settings UI)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);
        switch (requestCode) {
            // if result is from settings set the data for number of minutes and board size
            case REQUEST_CODE_SETTINGS:
                if (resultCode == RESULT_OK) {
                    numberOfMinutes = dataIntent.getIntExtra("minutes", 3);
                    boardSize = dataIntent.getIntExtra("boardSize", 4);
                }
        }
    }
}
