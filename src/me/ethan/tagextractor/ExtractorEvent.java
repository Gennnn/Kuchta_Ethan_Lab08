package me.ethan.tagextractor;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class ExtractorEvent {
    public static IntMap<String> wordCount;
    public static ArrayList<String> wordList;
    public static ArrayList<String> tagList;

    public static void extract(BufferedReader sourceFile, BufferedReader tagFile) throws IOException {
        wordCount = new IntMap<>();
        wordList = new ArrayList<String>();
        String line = "";
        List<String> words = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        ArrayList blacklist = new ArrayList();
        while ((line = tagFile.readLine()) != null) {
            tags = List.of(line.split("\\s+"));
            for (int i = 0; i < tags.size(); i++) {
                String tag = tags.get(i).toLowerCase(Locale.ROOT);
                if (!tag.isEmpty()) {
                    blacklist.add(tag);
                }
            }
        }
        line = "";
        while ((line = sourceFile.readLine()) != null) {
            words = List.of(line.split("\\s+"));
            for (int i =0; i < words.size(); i++) {
                String word = words.get(i).replaceAll("[^a-zA-Z ]", "").toLowerCase(Locale.ROOT);
                if (word.isEmpty()) {
                } else {
                    if (!blacklist.contains(word)) {

                        if (wordCount.contains(word)) {
                            wordCount.increment(word);
                        } else {
                            wordList.add(word);
                            wordCount.put(word, 1);
                        }
                    }
                }
            }
        }
    }
    public static String returnWords() {
        String printStr = "";
        Iterator var = wordCount.keySet().iterator();
        Map<String, Integer> newMap = new HashMap();
        newMap.putAll(wordCount.map);
        List<Map.Entry<String, Integer>> list = new LinkedList(newMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        IntMap sortedMap = new IntMap();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
            printStr = printStr + entry.getKey() + ": " + entry.getValue() + "\n";
        }


        return printStr;
    }

}
