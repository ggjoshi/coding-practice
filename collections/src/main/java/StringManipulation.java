import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringManipulation implements Comparable<StringManipulation> {
    public int compareTo(StringManipulation o) {
        return 0;
    }

    public void stringManipulator() {
        String str = "Hello world";
        System.out.println(str.intern());
        System.out.println();
        System.out.printf("%s - %d\n", "hello", 2);
        System.out.println(new StringBuilder(str).reverse().toString());
        str.hashCode();
        str.toUpperCase();
        String.valueOf(9);
        System.out.println();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing");
    }

    public static void main(String [] args) throws Throwable {
        StringManipulation stringManipulation = new StringManipulation();
        try {
            stringManipulation.stringManipulator();
            stringManipulation.finalize();
            stringManipulation = null;
            Set<String> mySet = new HashSet<String>();
            ParanthesisMatcher paranthesisMatcher = new ParanthesisMatcher();
            System.out.println(paranthesisMatcher.isBalanced("[]{()}"));
            System.out.println(3 % 2);
            System.out.println(-3 % 2);
            System.out.println(-3 % -2);
            System.out.println(3 % -2);
            Calendar calendar = new GregorianCalendar();
            System.out.print(calendar.getTimeZone().getOffset(1234l));
            List<String> strings = Arrays.asList("hello", "world");
            Iterator<String> stringIterator = strings.iterator();
            while(stringIterator.hasNext()) {
                System.out.println(stringIterator.next());
            }
            Set<String> set = new HashSet<String>();
            System.out.print(set.add("e"));
            System.out.print(set.add("e"));
            List<Integer> integerList = Arrays.asList(1, 2, 3);
            Map<Integer, String> integerStringMap = new HashMap<Integer, String>();
            integerStringMap.put(1, "hi");
            for (Map.Entry<Integer, String> entry : integerStringMap.entrySet()) {
                System.out.print(entry);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static class ParanthesisMatcher {
        private boolean isOpeningParanthesis(char symbol) {
            return (symbol == '[' || symbol == '{' || symbol == '(');
        }

        private boolean isClosingParanthesis(char symbol) {
            return (symbol == ']' || symbol == '}' || symbol == ')');
        }

        private boolean isMatchingParathesis(char symbol1, char symbol2) {
            return (symbol1 == '[' && symbol2 == ']') ||
                (symbol1 == '{' && symbol2 == '}') ||
                (symbol1 == '(' && symbol2 == ')');
        }

        public boolean isBalanced(String input) {
            if(input == null) {
                return false;
            }
            Deque<Character> deque = new ArrayDeque<Character>(input.length()/2);
            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                if(isOpeningParanthesis(currentChar)) {
                    deque.push(currentChar);
                } else if(isClosingParanthesis(currentChar) &&
                    (!deque.isEmpty() && isMatchingParathesis(deque.peek(), currentChar))) {
                    deque.pop();
                } else {
                    return false;
                }
            }
            return deque.isEmpty();
        }
    }
}
