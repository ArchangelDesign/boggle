package com.toreforge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board {
    private Tile[][] map = new Tile[4][4];
    private Dictionary dic;

    public Board(String inputFile, Dictionary dic) throws IOException {
        String lines[] = readFile(inputFile);
        for (int y=0; y<4; y++) {
            for(int x=0; x<4; x++) {
                map[x][y] = new Tile(lines[y].charAt(x));
                map[x][y].setCoords(x, y);
            }
        }
        this.dic = dic;
    }

    public List<Tile> getNeighbors(Tile tile, List<Tile> visited) {
        List<Tile> result = new ArrayList<>();

        // check left
        if (tile.getX() > 0)
            result.add(map[tile.getX()-1][tile.getY()]);
        // check top
        if(tile.getY() > 0)
            result.add(map[tile.getX()][tile.getY()-1]);
        //check left top
        if (tile.getX() > 0 && tile.getY() > 0)
            result.add(map[tile.getX()-1][tile.getY()-1]);
        // check right
        if (tile.getX() < 3)
            result.add(map[tile.getX()+1][tile.getY()]);
        // check bottom
        if (tile.getY() < 3)
            result.add(map[tile.getX()][tile.getY()+1]);
        // check right bottom
        if (tile.getX() < 3 && tile.getY() < 3)
            result.add(map[tile.getX()+1][tile.getY()+1]);
        // check top right
        if(tile.getY() > 0 && tile.getX() < 3)
            result.add(map[tile.getX()+1][tile.getY()-1]);
        // check left bottom
        if (tile.getX() > 0 && tile.getY() < 3)
            result.add(map[tile.getX()-1][tile.getY()+1]);

        for (Iterator<Tile> iter = result.listIterator(); iter.hasNext(); ) {
            Tile a = iter.next();
            for (Tile v : visited) {
                if (a.getX() == v.getX() && a.getY() == v.getY()) {
                    iter.remove();
                    break;
                }
            }
        }
        return result;
    }

    public void findWords() {
        for (int y=0; y<4; y++) {
            for (int x=0; x<4; x++) {
                System.out.println("processing " + x + ":" + y);
                processTile(map[x][y], new ArrayList<>());
            }
        }
    }

    private void processTile(Tile tile, List<Tile> visited) {
        List<Tile> neighbours = getNeighbors(tile, visited);
        List<Tile> newVisited = new ArrayList<>(visited);
        newVisited.add(tile);

        if (visited.size() >= 1) {
            dic.getWord(tile, visited);
        }

        for (Tile n : neighbours) {
            processTile(n, newVisited);
        }

    }

    public void printBoard() {
        System.out.println("-----------");
        for (int y=0; y<4; y++) {
            System.out.print("| ");
            for(int x=0; x<4; x++) {
                System.out.print(map[x][y].getValue() + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------");
    }

    private String[] readFile(String fileName) throws IOException {
        FileReader fileReader =
                new FileReader(fileName);

        String[] lines = new String[4];
        BufferedReader bufferedReader =
                new BufferedReader(fileReader);
        String line;
        int index = 0;
        while((line = bufferedReader.readLine()) != null) {
            lines[index] = line;
            index++;
        }

        bufferedReader.close();

        return lines;
    }
}
