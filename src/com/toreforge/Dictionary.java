package com.toreforge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dictionary {
    private List<String> dic = new ArrayList<>();
    private HashMap<String, Integer> foundWords = new HashMap<>();
    public static int count = 0;

    private HashMap<String, List<String>> newDic = new HashMap<>();

    public void loadDictionary(String fileName) throws IOException {
        FileReader fileReader =
                new FileReader(fileName);
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null) {
            if (line.length() < 2)
                continue;
            line = line.toLowerCase();
            if (!newDic.containsKey(line.substring(0, 2)))
                newDic.put(line.substring(0, 2), new ArrayList<>());
            newDic.get(line.substring(0, 2)).add(line);
        }

        bufferedReader.close();
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
