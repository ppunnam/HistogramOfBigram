package com.ehealth.HistogramOfBigram;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import static java.util.Arrays.asList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StringParserTest {

    @Test
    public void getWordsNullTest(){
        assertEquals(new ArrayList<>(), StringParser.getWords(null));
    }

    @Test
    public void getWordsEmptyStringTest(){
        assertEquals(new ArrayList<>(), StringParser.getWords(""));
    }

    @Test
    public void getWordsSingleWordTest(){
        assertEquals(asList("quick"), StringParser.getWords("Quick"));
    }

    @Test
    public void getWordsMultipleWordsTest(){
        assertEquals(asList("quick", "sand", "storm"), StringParser.getWords("quick sand storm"));
    }

    @Test
    public void getWordsSpecialCharacterTest(){
        assertEquals(asList("quick", "sand", "storm"), StringParser.getWords("quick sand, storm!"));
    }

    @Test
    public void getWordsSpecialCharacterMultipleSpacesTest(){
        assertEquals(asList("quick", "sand", "storm"), StringParser.getWords("quick sand,   storm!"));
    }

}
