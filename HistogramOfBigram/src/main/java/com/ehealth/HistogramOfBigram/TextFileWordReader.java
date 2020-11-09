package com.ehealth.HistogramOfBigram;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class TextFileWordReader implements Iterable<String> {

    private final String fileName;
    private final Function<String, List<String>> parser;

    public TextFileWordReader(final String fileName, Function<String, List<String>> stringParser) {
        assert fileName != null;
        assert stringParser!= null;

        this.fileName = fileName;
        this.parser = stringParser;
    }

    @Override
    public Iterator<String> iterator() {
        return new TextFileWordIterator();
    }

    private final class TextFileWordIterator implements Iterator<String>  {

        final Queue<String> words;
        LineIterator lineIterator;

        public TextFileWordIterator() {
            try {
                words = new LinkedList<String>();
                lineIterator = FileUtils.lineIterator(new File(fileName), "UTF-8");
                while(words.size() == 0 && lineIterator.hasNext()){
                    words.addAll(parser.apply(lineIterator.nextLine()));
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Override
        public boolean hasNext() {
            return words.size() > 0;
        }

        public String next() {
            try {

                //If last word read more words.
                while(words.size() == 1 && lineIterator.hasNext()) {
                    words.addAll(parser.apply(lineIterator.nextLine()));
                }

                //If no more lines to read cose the connection.
                if(!lineIterator.hasNext()){
                    lineIterator.close();
                }
                return words.poll();

            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}