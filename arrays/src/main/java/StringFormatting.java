/**
 *
 */
public class StringFormatting {
    public static void main(String[] args) {
        String template = "a %1$s %2$s %1$s";
        System.out.println(template);
        System.out.println(String.format(template, "foo", "bar"));
    }
}
