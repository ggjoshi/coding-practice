/**
 * Understand usage of String interning.
 */
public class StringInterner {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "world";
        String s4 = s1 + s3;
        s4.intern();
        String s5 = s1 + s3;
        s5.intern();
        System.out.println("Is s4 same as s5 ? => " + (s4 == s5));
        System.out.println("Is s1 same as s2 ? => " + (s1 == s2));
    }
}
