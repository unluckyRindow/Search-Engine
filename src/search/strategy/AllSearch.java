package search.strategy;

import java.util.*;

public class AllSearch implements SearchStrategy {


    @Override
    public Set<String> search(String pattern, ArrayList<String> data, Map<String, HashSet<Integer>> invertedIndexes) {
        Set<String> matchingLines = new HashSet<>();
        Set<Integer> indexes = new HashSet<>();
        String [] patternLowerCaseArray = pattern.toLowerCase().split(" ");

        indexes.addAll(invertedIndexes.getOrDefault(patternLowerCaseArray[0], new HashSet<>(Set.of(-1))));

        for (String word : patternLowerCaseArray){
            indexes.retainAll(invertedIndexes.getOrDefault(word, new HashSet<>(Set.of(-1))));
        }

        indexes.stream()
                .filter(index -> index > -1)
                .forEach(index -> matchingLines.add(data.get(index)));

        return matchingLines;
    }
}
