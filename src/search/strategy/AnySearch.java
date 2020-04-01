package search.strategy;

import java.util.*;

public class AnySearch implements SearchStrategy {


    @Override
    public Set<String> search(String pattern, ArrayList<String> data, Map<String, HashSet<Integer>> invertedIndexes) {
        Set<String> matchingLines = new HashSet<>();
        String [] patternLowerCaseArray = pattern.toLowerCase().split(" ");

        Arrays.stream(patternLowerCaseArray)
                .forEach(word -> {
                    invertedIndexes.getOrDefault(word, new HashSet<>(Set.of(-1)))
                            .stream()
                            .forEach(index -> {
                                if (index > -1) matchingLines.add(data.get(index));
                            });
                });
        return matchingLines;
    }
}
