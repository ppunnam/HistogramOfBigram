package com.ehealth.HistogramOfBigram;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TextFileWordReaderTest {

    @Test
    public void textFileWordReaderFilePathNullTest(){
        assertThrows(AssertionError.class, () ->new TextFileWordReader(null, StringParser::getWords) );
    }

    @Test
    public void textFileWordReaderParserNullTest(){
        assertThrows(AssertionError.class, () ->new TextFileWordReader("test.text", null) );
    }

}
