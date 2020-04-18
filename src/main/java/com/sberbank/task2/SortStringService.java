package com.sberbank.task2;

import java.util.*;
import java.util.stream.Collectors;

public class SortStringService {

    private Map<String, List<String>> dictionary = new TreeMap<>();

    public Map<String, List<String>> transformStringToSortedMap(String stringToMap) {

        List<String> stringToWords = Arrays.asList(stringToMap.split(" "));

        stringToWords.
                stream().
                collect(Collectors.groupingBy(word -> word.toLowerCase().substring(0, 1))).
                forEach((k,v) -> {
                    if (v.size() > 1){
                        v.sort((o1, o2) -> {
                            if (o1.length() < o2.length()) return 1;
                            else if (o1.length() > o2.length()) return -1;
                            else return o1.compareTo(o2);
                        });
                        dictionary.put(k,v);
                    }
                });
        return dictionary;
    }

}
