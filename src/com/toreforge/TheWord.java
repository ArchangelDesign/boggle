package com.toreforge;

import java.util.HashMap;

public class TheWord {
    private String key = null;
    private HashMap<String, TheWord> children = new HashMap<>();
    private boolean fullWord = false;

    public TheWord(String key) {
        this.key = key;
    }

    public boolean isRoot() {
        return key == null;
    }

    public String getKey() {
        return key;
    }

    public TheWord setKey(String key) {
        this.key = key;
        return this;
    }

    public HashMap<String, TheWord> getChildren() {
        return children;
    }

    public TheWord setChildren(HashMap<String, TheWord> children) {
        this.children = children;
        return this;
    }

    public boolean isFullWord() {
        return fullWord;
    }

    public TheWord setFullWord(boolean fullWord) {
        this.fullWord = fullWord;
        return this;
    }
}
