import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringUtil {
    public static void countCharMap(String s, Map<Character, Integer> charCountMap) {
        if(s == null) {
            return;
        }
        for(int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if(!charCountMap.containsKey(current)) {
                charCountMap.put(current, 0);
            }
            charCountMap.put(current, charCountMap.get(current) + 1);
        }
    }

    public static boolean isCharSubsetOf(String s1, String s2) {
        Map<Character, Integer> s1Map = new HashMap<Character, Integer>();
        Map<Character, Integer> s2Map = new HashMap<Character, Integer>();
        countCharMap(s1, s1Map);
        countCharMap(s2, s2Map);
        for(Map.Entry<Character, Integer> entry : s1Map.entrySet()) {
            if(!s2Map.containsKey(entry.getKey()) ||
                s2Map.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static class Node {
        public Node(int data) {
            this.data = data;
        }
        int data;
    }

    public static void main(String args[]) {
        String text = "good morning";
        String magazineText = "this is a good morningg";
        System.out.println(text + " can be constructed from " + magazineText + " = " +
            StringUtil.isCharSubsetOf(text, magazineText));
        Map<Integer, Node> integerNodeMap = new HashMap<Integer, Node>();
        integerNodeMap.put(1, new Node(5));
        Node value = integerNodeMap.get(1);
        System.out.println(" old val = " + value.data);
        value.data = 6;
        value = integerNodeMap.get(1);
        System.out.println(" old val = " + value.data);
    }
}