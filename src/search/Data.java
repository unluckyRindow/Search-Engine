package search;

import search.strategy.SearchStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


public class Data {

    private ArrayList<String> data;
    private Map<String, HashSet<Integer>> invertedIndexes;
    private SearchStrategy searchStrategy;
    public final String COLUMN_SEPARATOR;


    Data(String COLUMN_SEPARATOR){
        data = new ArrayList<>();
        invertedIndexes = new HashMap<>();
        this.COLUMN_SEPARATOR = COLUMN_SEPARATOR;
    }


    Data(BufferedReader bf, String COLUMN_SEPARATOR) throws IOException {
        this(COLUMN_SEPARATOR);

        String line;
        int lineCount = 0;
        while ((line = bf.readLine()) != null) {
            String [] arrayLine = line.split(COLUMN_SEPARATOR);

            for (String word : arrayLine){
                String wordLowerCase = word.toLowerCase();
                if (!invertedIndexes.keySet().isEmpty() && invertedIndexes.keySet().contains(wordLowerCase)){
                    invertedIndexes.get(wordLowerCase).add(lineCount);
                } else {
                    invertedIndexes.put(wordLowerCase, new HashSet<>(Set.of(lineCount)));
                }
            }
            data.add(line);
            lineCount++;
        }
    }


    public SearchStrategy getSearchStrategy() {
        return searchStrategy;
    }


    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }


    public Set<String> search(String pattern){
        return this.searchStrategy.search(pattern, data, invertedIndexes);
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        for (String line : data){
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();
    }
}
