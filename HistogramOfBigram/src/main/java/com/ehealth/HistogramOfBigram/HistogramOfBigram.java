package com.ehealth.HistogramOfBigram;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class HistogramOfBigram {

    public static LinkedHashMap<String, Integer> getBigramMap(final String filePath) throws IOException {

        if(StringUtils.isEmpty(filePath) || Files.notExists(Path.of(filePath))){
            throw new FileNotFoundException();
        }

        Iterator<String> fileWordIterator =  new TextFileWordReader(filePath, StringParser::getWords).iterator();
        return getBigramMapByIterator(fileWordIterator);
    }

    public static LinkedHashMap<String, Integer> getBigramMapByIterator(Iterator<String> fileWordIterator){

        if(fileWordIterator == null)
            return new LinkedHashMap<>();

        String previousWord = null;
        LinkedHashMap<String, Integer> bigramMap = new LinkedHashMap<>();

        while(fileWordIterator.hasNext()){
            String currentWord = fileWordIterator.next();

            if(previousWord == null)
            {
                previousWord = currentWord;
                continue;
            }

            String bigram = previousWord + " " + currentWord;
            if(bigramMap.containsKey(bigram)){
                bigramMap.put(bigram, bigramMap.get(bigram) + 1);
            } else {
                bigramMap.put(bigram, 1);
            }

            previousWord = currentWord;
        }

        return bigramMap;
    }

}
