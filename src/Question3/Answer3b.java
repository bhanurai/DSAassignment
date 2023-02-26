package Question3;
public class Answer3b {
    public static boolean matchString(String a, String pattern) {
        int i = 0, j = 0; // initialize pointers for string and pattern
        int n = a.length(), m = pattern.length(); // get length of string and pattern

        while (i < n && j < m) { // loop until either string or pattern ends
            if (pattern.charAt(j) == '@') { // check for '@' pattern
                return true; // entire string matches
            } else if (pattern.charAt(j) == '#') { // check for '#' pattern
                i++; // increment pointer for string
                j++; // increment pointer for pattern
            } else if (a.charAt(i) != pattern.charAt(j)) { // check for mismatched characters
                return false; // entire string does not match
            } else { // if characters match
                i++; // increment pointer for string
                j++; // increment pointer for pattern
            }
        }

        if (i == n && j == m) { // check if both string and pattern are fully traversed
            return true; // entire string matches
        } else if (j == m - 1 && pattern.charAt(j) == '#') { // check if last pattern is '#'
            return true; // entire string matches
        } else { // otherwise
            return false; // entire string does not match
        }
    }
    public static void main(String[] args) {
        String a1 = "tt";
        String pattern1 = "@";
        System.out.println(matchString(a1, pattern1)); // true

        String a2 = "ta";
        String pattern2 = "t";
        System.out.println(matchString(a2, pattern2)); // false

        String a3 = "ta";
        String pattern3 = "t#";
        System.out.println(matchString(a3, pattern3)); // true
    }

}
