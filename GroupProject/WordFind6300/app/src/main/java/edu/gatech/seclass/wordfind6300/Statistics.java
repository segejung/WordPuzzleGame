package edu.gatech.seclass.wordfind6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics extends AppCompatActivity {

    // sorting structure should be implemented here.
    List<Integer> indexRankListOfScores = new ArrayList();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        String text;
        StatObject so = this.readFile();

        indexRankListOfScores.add(0);
        for(int i = 1; i < so.finalScores.size(); i++){
            int bestScoreIndex = indexRankListOfScores.get(0);
            int bestScore = so.finalScores.get(bestScoreIndex);
            int currentScore = so.finalScores.get(i);
            if(currentScore > bestScore){
                indexRankListOfScores.add(0, i);
            } else{
                indexRankListOfScores.add(indexHelper(so.finalScores, indexRankListOfScores, currentScore), i);
            }
        }

        TextView firstScore = (TextView) findViewById(R.id.textView24);
        TextView secondScore = (TextView) findViewById(R.id.textView10);
        TextView thirdScore = (TextView) findViewById(R.id.textView16);
        TextView fourthScore = (TextView) findViewById(R.id.textView20);

        TextView firstResTimes = (TextView) findViewById(R.id.textView25);
        TextView secondResTimes = (TextView) findViewById(R.id.textView11);
        TextView thirdResTimes = (TextView) findViewById(R.id.textView17);
        TextView fourthResTimes = (TextView) findViewById(R.id.textView21);

        TextView firstWordCount = (TextView) findViewById(R.id.textView26);
        TextView secondWordCount = (TextView) findViewById(R.id.textView12);
        TextView thirdWordCount = (TextView) findViewById(R.id.textView18);
        TextView fourthWordCount = (TextView) findViewById(R.id.textView22);

        if(indexRankListOfScores.size() > 0){
            index = indexRankListOfScores.get(0);
            firstScore.setText(so.finalScores.get(index).toString());
            firstResTimes.setText(so.resetCounts.get(index).toString());
            firstWordCount.setText(so.wordCounts.get(index).toString());
        }
        if(indexRankListOfScores.size() > 1){
            index = indexRankListOfScores.get(1);
            secondScore.setText(so.finalScores.get(index).toString());
            secondResTimes.setText(so.resetCounts.get(index).toString());
            secondWordCount.setText(so.wordCounts.get(index).toString());
        }
        if(indexRankListOfScores.size() > 2){
            index = indexRankListOfScores.get(2);
            thirdScore.setText(so.finalScores.get(index).toString());
            thirdResTimes.setText(so.resetCounts.get(index).toString());
            thirdWordCount.setText(so.wordCounts.get(index).toString());
        }
        if(indexRankListOfScores.size() > 3){
            index = indexRankListOfScores.get(3);
            fourthScore.setText(so.finalScores.get(index).toString());
            fourthResTimes.setText(so.resetCounts.get(index).toString());
            fourthWordCount.setText(so.wordCounts.get(index).toString());
        }

        TextView firstWord = (TextView) findViewById(R.id.word1);
        TextView secondWord = (TextView) findViewById(R.id.word2);
        TextView thirdWord = (TextView) findViewById(R.id.word3);

        List<String> rank = new ArrayList();

        for(Map.Entry entry: so.allWordsMap.entrySet()){
            if(rank.size() == 0){
                rank.add(entry.getKey().toString());
                continue;
            }
            String first = rank.get(0);
            int frequency = Integer.valueOf(entry.getValue().toString());
            int firstFrequency =  so.allWordsMap.get(first);
            if( frequency > firstFrequency){
                rank.add(0, entry.getKey().toString());
            } else if (frequency == firstFrequency){
                rank.add(1, entry.getKey().toString());
            }
        }

        TextView firstWordStat = (TextView) findViewById(R.id.word10);
        TextView secondWordStat = (TextView) findViewById(R.id.word20);
        TextView thirdWordStat = (TextView) findViewById(R.id.word30);

        String word = "";
        if(rank.size() > 0){
            word = rank.get(0);
            firstWord.setText(word);
            firstWordStat.setText(so.allWordsMap.get(word).toString());
        }
        if(rank.size() > 1){
            word = rank.get(1);
            secondWord.setText(word);
            secondWordStat.setText(so.allWordsMap.get(word).toString());
        }
        if(rank.size() > 2){
            word = rank.get(2);
            thirdWord.setText(word);
            thirdWordStat.setText(so.allWordsMap.get(word).toString());
        }
    }

    public void handleClick(View view) {
        TextView settingMinute = (TextView) findViewById(R.id.textView4);
        TextView settingSize = (TextView) findViewById(R.id.textView9);
        if (view.getId()== R.id.button4){
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 1){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            index = indexRankListOfScores.get(0);
            text = so.minutesList.get(index).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(index).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button5) {
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 2){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            index = indexRankListOfScores.get(1);
            text = so.minutesList.get(index).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(index).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button6) {
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 3){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            index = indexRankListOfScores.get(2);
            text = so.minutesList.get(index).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(index).toString();
            settingSize.setText(text);
        }
        else if (view.getId()== R.id.button7) {
            String text;
            StatObject so = this.readFile();
            if(so.minutesList.size() < 4){
                Toast.makeText(getApplicationContext(), "No game data yet", Toast.LENGTH_SHORT).show();
                return;
            }
            index = indexRankListOfScores.get(3);
            text = so.minutesList.get(index).toString();
            settingMinute.setText(text);
            text = so.sizeList.get(index).toString();
            settingSize.setText(text);
        }
    }

    public StatObject readFile(){
        StatObject stat = null;
        try{
            // Reading the object from a file
            FileInputStream file = openFileInput("data.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object
            stat = (StatObject) in.readObject();

            in.close();
            file.close();
//            displayText.setText("the last value stored in the allWordsMap is \n" + stat.allWordsMap.get(stat.allWordsMap.size() - 1));
        } catch(IOException ex){
            stat =  new StatObject();
        } catch(ClassNotFoundException ex){
            Toast.makeText(getApplicationContext(), "ClassNotFoundException is caught", Toast.LENGTH_SHORT).show();
        }
        return stat;
    }
    private int indexHelper(List<Integer> finalScores, List<Integer> list, int currentScore){
        int i = 1;
        while(i < list.size()){
            if(currentScore > finalScores.get(i)){
                return i;
            }
            i++;
        }
        return i;
    }
}
