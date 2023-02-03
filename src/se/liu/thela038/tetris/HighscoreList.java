package se.liu.thela038.tetris;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HighscoreList
{
    private List<Highscore> highscoreList = new ArrayList<>();
    public HighscoreList() {
    }


    private void sort(){
        highscoreList.sort(new ScoreComparator());
    }

    public void addHighscore(Highscore highscore){
        highscoreList.add(highscore);
        sort();
    }

    public List<Highscore> getHighscoreList() {
	return highscoreList;
    }

    public void saveHighscores() throws JsonIOException, IOException {
        Gson gson = new Gson();
        Path tempFile = Paths.get("temp.json");
        Path newName = Paths.get("highscores.json");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()))){
            gson.toJson(this, writer);
        }
        try{
            Files.deleteIfExists(newName);
            Files.move(tempFile, newName);
        }catch (IOException e){
            e.printStackTrace();
            throw e;
        }
    }

    public static HighscoreList readHighscores() throws JsonIOException, IOException {
        Gson gson = new Gson();
        try(BufferedReader reader = new BufferedReader(new FileReader("highscores.json"))){
            return gson.fromJson(reader, HighscoreList.class);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return new HighscoreList();
        }
    }
}
