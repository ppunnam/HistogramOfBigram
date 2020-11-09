package com.ehealth.HistogramOfBigram;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class StringParser  {
    public static List<String> getWords(String s){
        List<String> result = new ArrayList<String>();
        if(StringUtils.isEmpty(s))
            return result;

        String[] words = s.split("\\s+");
        for (String word : words) {
            result.add(word.replaceAll("[^\\w]", "").toLowerCase());
        }
        return result;
    }
}
