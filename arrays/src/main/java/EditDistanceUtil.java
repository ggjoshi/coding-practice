public class EditDistanceUtil {
    public boolean isOneEditAway(String s1, String s2) {
        System.out.println("s1 = " + s1  + ", s2 = " + s2);
        if(s1 == null || s2 == null) {
            return s1 == s2;
        }
        int len1 = s1.length(), len2 = s2.length();

        if(Math.abs(len1 - len2) > 1) {
            return false;
        }

        String big = (len1 > len2) ? s1 : s2;
        String small = (len1 > len2) ? s2 : s1;
        int bIndex = 0, sIndex = 0;
        boolean isOneEditDone = false;
        while(sIndex < small.length()) {
            if(big.charAt(bIndex) != small.charAt(sIndex)) {
                if(isOneEditDone) {
                    return false;
                }
                isOneEditDone = true;
                // Replace char
                if(len1 == len2) {
                    sIndex++;
                }
                // Remove char from bigger string
            } else {
                sIndex++;
            }
            bIndex++;
        }
        return true;
    }

    public static void main(String[] args) {
        EditDistanceUtil editDistanceUtil = new EditDistanceUtil();
        System.out.println(editDistanceUtil.isOneEditAway("abc", "abc"));
        System.out.println(editDistanceUtil.isOneEditAway("abc", "abe"));
        System.out.println(editDistanceUtil.isOneEditAway("abc", "ab"));
        System.out.println(editDistanceUtil.isOneEditAway("abc", "bc"));
        System.out.println(editDistanceUtil.isOneEditAway("abc", "ac"));
        System.out.println(editDistanceUtil.isOneEditAway("a", "abc"));
        System.out.println(editDistanceUtil.isOneEditAway("abcde", "abc"));
    }
}