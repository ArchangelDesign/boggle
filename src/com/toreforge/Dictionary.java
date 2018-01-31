package com.toreforge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class Dictionary {
    private List<String> dic = new ArrayList<>();
    private HashMap<String, Integer> foundWords = new HashMap<>();
    public static int count = 0;
    private TheWord theDictionary = new TheWord(null);

    private HashMap<String, List<String>> newDic = new HashMap<>();

    public void loadDictionary(String fileName) throws IOException {
        FileReader fileReader =
                new FileReader(fileName);
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            loadWord(line.toLowerCase());
        }

        bufferedReader.close();
    }

    public void loadWord(String word) {
        char[] w = word.toCharArray();

        putWord(w, 0, w.length, theDictionary);
    }

    private void putWord(char[] word, int index, int size, TheWord dictionary) {
        if (index >= size) {
            dictionary.setFullWord(true);
            return;
        }

        dictionary.setKey(String.valueOf(word[index]));

        if (!dictionary.getChildren().containsKey(String.valueOf(word[index])))
            dictionary.getChildren().put(String.valueOf(word[index]), new TheWord(String.valueOf(word[index])));
        putWord(word, index + 1, size, dictionary.getChildren().get(String.valueOf(word[index])));
    }

    private boolean wordExists(String word) {
        if (!newDic.containsKey(word.substring(0, 2)))
            return false;
        for (String s : newDic.get(word.substring(0, 2))) {
            if (s.equalsIgnoreCase(word)) {
                if (foundWords.containsKey(word))
                    return true;
                count++;
                System.out.println("FOUND " + word);
                foundWords.put(word, 1);
                return true;
            }
        }
        return false;
    }

    public void getWord(Tile t, List<Tile> letters) {
        String w = "";
        for (Tile l : letters)
            w += l.getValue();
        w += t.getValue();

        wordExists(w.toLowerCase());
    }
}
