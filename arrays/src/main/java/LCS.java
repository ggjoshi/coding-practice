/**
 * Helper to find LCS of two strings.
 * e.g. for S1 = abcd and S2 = alcm
 * the result will be ac
 */
public class LCS {
    public static String findLCS(String s1, String s2) {
        if(s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
            return null;
        }

        int [][] lcsOutput = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length() + 1; i++) {
            for(int j = 0; j < s2.length() + 1; j++) {
                if(i == 0 || j == 0) {
                    lcsOutput[i][j] = 0;
                    continue;
                }
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    lcsOutput[i][j] = lcsOutput[i - 1][j - 1] + 1;
                } else {
                    lcsOutput[i][j] = Math.max(lcsOutput[i - 1][j], lcsOutput[i][j - 1]);
                }
            }
        }
        int currentI = s1.length(), currentJ = s2.length();
        StringBuilder lcs = new StringBuilder();
        while(currentI >= 1 &&
            currentJ >= 1) {
            if(lcsOutput[currentI][currentJ] ==
                lcsOutput[currentI - 1][currentJ - 1] + 1) {
                lcs.append(s1.charAt(currentI - 1));
                currentI = currentI - 1;
                currentJ = currentJ - 1;
            } else if(lcsOutput[currentI][currentJ] ==
                lcsOutput[currentI - 1][currentJ]) {
                currentI = currentI - 1;
            } else {
                currentJ = currentJ - 1;
            }
        }
        return lcs.reverse().toString();
    }

    public static void main(String [] args) {
        System.out.println(findLCS("abcd", "acdb"));
    }
}
