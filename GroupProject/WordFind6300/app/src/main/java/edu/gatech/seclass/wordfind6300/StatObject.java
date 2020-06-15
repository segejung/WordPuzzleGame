package edu.gatech.seclass.wordfind6300;

import android.app.Application;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatObject extends Application implements Serializable {
    List<Integer> finalScores;
    List<Integer> resetCounts;
    List<Integer> wordCounts;
    List<Integer> sizeList;
    List<Integer> minutesList;
    List<Map<Character, Integer>> letterWeights;
    Map<String, Integer> allWordsMap;

    int finalScore = 0;
    int resetCount = 0;
    int wordCount = 0;
    int size = 4;
    int minutes = 3;
    Map<Character, Integer> letterWeight;

    final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public StatObject(){
        finalScores = new ArrayList();
        resetCounts = new ArrayList();
        wordCounts = new ArrayList();
        sizeList = new ArrayList();
        minutesList = new ArrayList();
        letterWeight = new HashMap();
        // Default weights of all letters to 1
        for (Character letter : LETTERS.toCharArray()) {
            letterWeight.put(letter, 1);
        }
        letterWeights = new ArrayList();
        allWordsMap = new HashMap();
    }
    public void updateScores(int finalScore){
        this.finalScore = finalScore;
    }
    public void updateResetCounts(int resetCount){
        this.resetCount = resetCount;
    }
    public void updateWordCounts(int wordCount){
        this.wordCount = wordCount;
    }
    public void updateSizeList(int size){
        this.size = size;
    }
    public void updateMinutes(int minutes){
        this.minutes = minutes;
    }
    public void saveAllToList(){
        finalScores.add(finalScore);
        resetCounts.add(resetCount);
        wordCounts.add(wordCount);
        sizeList.add(size);
        minutesList.add(minutes);
        finalScore = 0;
        resetCount = 0;
        wordCount = 0;
    }
}
