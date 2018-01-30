package com.toreforge;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) throws IOException {
        Date date = new Date();
        System.out.println(sdf.format(date));
	    System.out.println("Processing");
	    String inputFile = args[0];
	    String dictionaryFile = args[1];
	    Dictionary d = new Dictionary();
//	    d.loadDictionary(dictionaryFile);
	    d.loadDictionary(dictionaryFile);
        Board board = new Board(inputFile, d);
        board.printBoard();
        board.findWords();
        System.out.println("Found " + Dictionary.count + " words");
        Date date2 = new Date();
        System.out.println(sdf.format(date2));
        System.in.read();
    }
}
