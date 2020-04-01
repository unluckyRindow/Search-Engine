package search.strategy;

import java.util.*;
import java.util.stream.IntStream;

public class NoneSearch implements SearchStrategy {


    @Override
    public Set<String> search(String pattern, ArrayList<String> data, Map<String, HashSet<Integer>> invertedIndexes) {
        Set<String> matchingLines = new HashSet<>();
        HashSet<Integer> notMatchingIndexes = new HashSet<>();
        String [] patternLowerCaseArray = pattern.toLowerCase().split(" ");

        Arrays.stream(patternLowerCaseArray)
                .forEach(word -> {
                    notMatchingIndexes.addAll(invertedIndexes.getOrDefault(word, new HashSet<>(Set.of(-1))));
                });

        IntStream.range(0, data.size())
                .filter(index -> !notMatchingIndexes.contains(index))
                .forEach(index -> matchingLines.add(data.get(index)));

        return matchingLines;
    }
}
