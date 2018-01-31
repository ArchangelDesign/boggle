package com.toreforge.test;

import com.toreforge.Dictionary;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DictionaryTest {
    private Dictionary dic;

    @Before
    public void setUp() throws Exception {
        dic = new Dictionary();
    }

    @Test
    public void wordAddTest() {
        dic.loadWord("test");
    }

}