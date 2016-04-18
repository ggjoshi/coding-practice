import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordUtil {
    private static class Dictionary {
        private static final Set<String> dictionary = new HashSet<String>(Arrays.asList("cat", "cog", "dog", "cot"));
        public static boolean isValidWord(String word) {
            return dictionary.contains(word);
        }
    }

    private List<String> getNeighboringWords(String word) {
        List<String> neighbors = new ArrayList<String>();
        StringBuilder temp = new StringBuilder(word);
        for(int i = 0; i < word.length(); i++) {
            for(char j = 'a'; j <= 'z'; j++) {
                if(j == word.charAt(i)) {
                    continue;
                }
                temp.setCharAt(i, j);
                String tempWord = temp.toString();
                if(!Dictionary.isValidWord(tempWord)) {
                    continue;
                }
                neighbors.add(tempWord);
            }
            temp.setCharAt(i, word.charAt(i));
        }
        return neighbors;
    }

    private void printPath(String word, Map<String, String> parentMap) {
        String parent = parentMap.get(word);
        if(!parent.equals(word)) {
            printPath(parent, parentMap);
        }
        System.out.print(word + " ");
    }

    /*
     * Transform from word1 to word2 by going through a series of valid words.
     */
    public void transform(String word1, String word2) {
        Queue<String> bfsQueue = new ArrayDeque<String>();
        Set<String> visitedSoFar = new HashSet<String>();
        Map<String, String> parentMap = new HashMap<String, String>();
        bfsQueue.offer(word1);
        visitedSoFar.add(word1);
        parentMap.put(word1, word1);
        while(!bfsQueue.isEmpty()) {
            String current = bfsQueue.poll();
            for(String neighbor : getNeighboringWords(current)) {
                if(visitedSoFar.contains(neighbor)) {
                    continue;
                }
                bfsQueue.offer(neighbor);
                parentMap.put(neighbor, current);
                visitedSoFar.add(neighbor);
                if(neighbor.equals(word2)) {
                    break;
                }
            }
        }
        if(!parentMap.containsKey(word2)) {
            System.out.println("Can not find a path from src to dest.");
        } else {
            System.out.println("Path is :-");
            printPath(word2, parentMap);
        }
    }

    public static void main(String[] args) {
        WordUtil wordUtil = new WordUtil();
        wordUtil.transform("cat", "dog");
    }
}