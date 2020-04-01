package search.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface SearchStrategy {
    Set<String> search(String pattern, ArrayList<String> data, Map<String, HashSet<Integer>> invertedIndexes);
}
