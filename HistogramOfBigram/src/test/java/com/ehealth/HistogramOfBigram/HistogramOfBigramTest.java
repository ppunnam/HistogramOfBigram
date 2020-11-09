package com.ehealth.HistogramOfBigram;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HistogramOfBigramTest {

    @Mock
    Iterator<String> textFileWordReader;

    @Test
    public void getBigramNullFilePathTest(){
        assertThrows(FileNotFoundException.class, () -> HistogramOfBigram.getBigramMap(null));
    }

    @Test
    public void getBigramFileNotExistsTest(){
        assertThrows(FileNotFoundException.class, () -> HistogramOfBigram.getBigramMap("test1.text"));
    }

    @Test
    public void getBigramFileByIteratorNullTest(){
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> result =  HistogramOfBigram.getBigramMapByIterator(null);
        assertTrue(map.equals(result));
    }

    @Test
    public void getBigramFileByIterator(){
        Mockito.when(textFileWordReader.hasNext()).thenReturn(true, true, false);
        Mockito.when(textFileWordReader.next()).thenReturn("quick", "sand");
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("quick sand", 1);
        LinkedHashMap<String, Integer> result =  HistogramOfBigram.getBigramMapByIterator(textFileWordReader);
        assertTrue(map.equals(result));
    }

    @Test
    public void getBigramFileByIteratorSingleWord(){
        Mockito.when(textFileWordReader.hasNext()).thenReturn(true, false);
        Mockito.when(textFileWordReader.next()).thenReturn("quick" );
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> result =  HistogramOfBigram.getBigramMapByIterator(textFileWordReader);
        assertTrue(map.equals(result));
    }

    @Test
    public void getBigramFileByIteratorTest(){
        Mockito.when(textFileWordReader.hasNext()).thenReturn(true, true, true, true, true, true, true, true, true, false);
        Mockito.when(textFileWordReader.next()).thenReturn("the","quick", "brown", "fox", "and", "the", "quick", "blue", "hare");
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("the quick", 2);
        map.put("quick brown", 1);
        map.put("brown fox", 1);
        map.put("fox and", 1);
        map.put("and the", 1);
        map.put("quick blue", 1);
        map.put("blue hare", 1);
        LinkedHashMap<String, Integer> result =  HistogramOfBigram.getBigramMapByIterator(textFileWordReader);
        assertTrue(map.equals(result));
    }

}
