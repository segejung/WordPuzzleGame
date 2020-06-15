package edu.gatech.seclass.wordfind6300;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Collections;
import java.util.Set;


import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.WindowManager;

public class WordGame extends AppCompatActivity {
    GridView board;

    int numberOfMinutes, boardSize, lettersCount, positionLastClicked = 0;
    Button enterBtn, cancelBtn, rerollBtn;
    TextView wordInput;
    TextView scoreText;
    TextView countDownText;
    CountDownTimer countDownTimer;
    long timeLeftInMilliseconds;

    public int finalScore = 0;
    //set the random seed to help with automated testing
    Random r = new Random(6300);

    List<String> list;
    List<Integer> positionsClicked;
    Set<String> wordSet;

    Button endGameBtn;
    final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StatObject so;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);
        so = ((StatObject)this.getApplication());

        // Keeps the display of components from shifting when the soft keyboard displays when typing
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        numberOfMinutes = so.minutes;
        timeLeftInMilliseconds = (long)numberOfMinutes * 60000; //3 minutes
        boardSize = so.size;
        lettersCount = boardSize * boardSize;

        // randomly generates the board
        generateRandom(r);

        // timer start
        startTimer();
        board = findViewById(R.id.boardGrid);

        // Set the number of columns of the board
        board.setNumColumns(boardSize);

        // Set the board with the data from the randomized list of letters
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        board.setAdapter(adapter);

        positionsClicked = new ArrayList<>();
        board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // If letters are already added need to check for constraints
                if (!wordInput.getText().equals("")) {
                    // Check if the position clicked on has already been selected
                    if (positionsClicked.contains(position)) {
                        Toast.makeText(getApplicationContext(), "Single letter on the board cannot be used twice!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Check if the current selected position is adjacent to the previous
                        if (isAdjacent(boardSize, position, positionLastClicked)) {
                            addLetter(v, position);
                        } else {
                            Toast.makeText(getApplicationContext(), "The letters must be adjacent to each other!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    // If no letters have been added add letter to wordInput
                    addLetter(v, position);
                }
            }
        });

        //Initializing
        rerollBtn = findViewById(R.id.rerollBtn);
        enterBtn = findViewById(R.id.enterBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        wordInput = findViewById(R.id.wordInput);
        scoreText = findViewById(R.id.scoreText);
        countDownText = findViewById(R.id.countDownText);

        wordSet = new HashSet();
        enterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String word = String.valueOf(wordInput.getText());

                if (word.length() <= 1) {
                    Toast.makeText(getApplicationContext(), "The word must contain two or more letters!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (wordSet.add(word)) {
                        finalScore += word.length();
                        scoreText.setText(String.valueOf(finalScore));
                        wordInput.setText("");
                        positionsClicked.clear();
                        so.wordCount++;
                    } else {
                        Toast.makeText(getApplicationContext(), "The word has been used in this game!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                wordInput.setText("");
                positionsClicked.clear();
            }
        });

        // The reroll button makes the board regenerate and subtract 5 points.
        rerollBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                generateRandom(r);
                board = findViewById(R.id.boardGrid);
                so.resetCount++;

                // Set the number of columns of the board
                board.setNumColumns(boardSize);

                // Set the board with the data from the randomized list of letters
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, list);

                board.setAdapter(adapter);

                //deducts 5 points
                finalScore -= 5;
                scoreText.setText(String.valueOf(finalScore));
                wordInput.setText("");
                positionsClicked.clear();

            }
        });

        endGameBtn = findViewById(R.id.endGameBtn);
        endGameBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                endGame();
            }
        });
    }

    // method for startTimer
    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    // method for updating timer
    public void updateTimer () {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countDownText.setText(timeLeftText);
    }

    public void generateRandom(Random r){
        list = new ArrayList<>();

        String consonants = "";
        String vowels = "";

        for(Character c : LETTERS.toCharArray()){
            for (int i = 0; i < so.letterWeight.getOrDefault(c, 1); i++) {
                String C = Character.toString(c);
                if ("AEIOU".indexOf(C) >= 0) {
                    vowels += C;
                } else {
                    consonants += C;
                }
            }
        }

        int consonantCount = 0;
        int vowelCount = 0;

        // Take vowels string as example, it will ended up being "AAAEIOU" if A is weighted as 3.
        // Same as the consonants string.
        // When getting a random char from the string, the value is weighted.


        // Set the consonantCount to 80% of the board size and vowelCount to 20% of the board size
        // rounded up
        consonantCount = (int) Math.floor((double) lettersCount * .80);
        vowelCount = (int) Math.ceil((double) lettersCount * .20);

//        Random r = new Random(6300);

        for (int i = 0; i < consonantCount; i++){
            char c = consonants.charAt(r.nextInt(consonants.length()));
            if(c == 'Q'){
                list.add("Qu");
            } else{
                list.add(Character.toString(c)); // Add consonant
            }
        }

        // Loop through the vowelCount and add a random vowel
        for (int i = 0; i < vowelCount; i++) {
            list.add(Character.toString(vowels.charAt(r.nextInt(vowels.length())))); // Add vowel
        }

        // Shuffle the list to randomize the board

        Collections.shuffle(list, r);
    }

    // c = number of columns
    // a = index of a
    // b = index of b
    // Check if elements are adjacent to each other in the board
    public static boolean isAdjacent(int c, int a, int b) {
        // columns and rows of a and b
        int ax = a % c;
        int ay = a / c;
        int bx = b % c;
        int by = b / c;

        // Difference of row and column must be no more than 1
        return a != b && Math.abs(ax - bx) <= 1 && Math.abs(ay - by) <= 1;
    }

    // Adds selected letter to wordInput
    public void addLetter(View v, int position) {
        String originalText = String.valueOf(wordInput.getText());
        wordInput.setText(originalText + ((TextView) v).getText());
        positionLastClicked = position;
        positionsClicked.add(position);
    }
    private void endGame(){
        countDownTimer.cancel();
        countDownText.setText("0:00");

        for (String word : wordSet) {
            int count = so.allWordsMap.getOrDefault(word, 0);
            so.allWordsMap.put(word, count + 1);
        }
        Toast.makeText(getApplicationContext(), "Showing all words and the count in the allWordsMap", Toast.LENGTH_SHORT).show();
        for (Map.Entry<String, Integer> entry : so.allWordsMap.entrySet()) {
            Toast.makeText(getApplicationContext(), entry.getKey() + " " + entry.getValue(), Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(getApplicationContext(), "All entries displayed", Toast.LENGTH_SHORT).show();
        so.finalScore = this.finalScore;

        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(this);

        // Set the message show for the Alert time
        builder.setMessage("Final Score = " + this.finalScore);

        // Set Alert Title
        builder.setTitle("Game Ended");

        // Set Cancelable false
        // for when the user clicks on the outside
        // the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name
        // OnClickListener method is use of
        // DialogInterface interface.

        builder
                .setPositiveButton(
                        "OK",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // When the user click yes button
                                // then app will close
                                finish();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();

        writeFile();
        finalScore = 0;
        wordInput.setText("");
        positionsClicked.clear();
        so.wordCount = 0;
        wordSet = new HashSet();

        try{ Looper.loop(); }
        catch(RuntimeException e){}

    }

    public void writeFile(){
        if(so == null){
            so = this.readFile();
        }
        try{
            //Saving of object in a file
            FileOutputStream file = openFileOutput("data.ser", MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(file);

            so.saveAllToList();
            // Method for serialization of object
            out.writeObject(so);
            out.close();
            file.close();

            Toast.makeText(getApplicationContext(), "Object has been serialized", Toast.LENGTH_SHORT).show();
        } catch(IOException ex){
            Toast.makeText(getApplicationContext(), "IOException is caught", Toast.LENGTH_SHORT).show();
        }
    }
    public StatObject readFile(){
        try{
            // Reading the object from a file
            FileInputStream file = openFileInput("data.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
            so = (StatObject) in.readObject();

            in.close();
            file.close();
//            displayText.setText("the last value stored in the allWordsMap is \n" + stat.allWordsMap.get(stat.allWordsMap.size() - 1));
        } catch(IOException ex){
            Toast.makeText(getApplicationContext(), "IOException is caught, initializing a new StatObject", Toast.LENGTH_SHORT).show();
            so =  new StatObject();
        } catch(ClassNotFoundException ex){
            Toast.makeText(getApplicationContext(), "ClassNotFoundException is caught", Toast.LENGTH_SHORT).show();
        }
        return so;
    }

    // When back button is pressed at the bottom left of screen, return data from settings back to
    // Main UI
    @Override
    public void onBackPressed() {
        endGame();
    }

    // When the back button at the top left of the screen is clicked, return data from settings back
    // to Main UI
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                endGame();
        }

        return(super.onOptionsItemSelected(item));
    }
}

