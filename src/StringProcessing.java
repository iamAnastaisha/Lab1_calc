import javax.swing.*;

public class StringProcessing {

    public static boolean isStringOk(JFrame jFrame, String str, int num) {
        if (str == null || str.equals("")) {
            JOptionPane.showMessageDialog(jFrame, "Enter " + num + " number!", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int countDots = 0;
        boolean isSpacesOk = true;
        if (str.indexOf(' ') != -1) isSpacesOk = checkSpaces(str);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(0) == '-') continue;
            if (str.charAt(i) == ',' || str.charAt(i) == '.') countDots++;
            if ((!Character.isDigit(str.charAt(i)) && str.charAt(i) != ',' && str.charAt(i) != '.' && str.charAt(i) != ' ')
                    || countDots > 1 || !isSpacesOk) {
                JOptionPane.showMessageDialog(jFrame, "Incorrect input!", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    private static boolean checkSpaces(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        boolean isAfterDot = sb.indexOf(".") == -1 && sb.indexOf(",") == -1;
        int counter = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '.' || sb.charAt(i) == ',') {
                isAfterDot = true;
                continue;
            }
            if (!isAfterDot) {
                if (sb.charAt(i) == ' ') return false;
            } else {
                if (sb.charAt(i) != ' ') {
                    counter++;
                    if (counter > 3) return false;
                } else {
                    if (counter != 3) return false;
                    else counter = 0;
                }
            }
        }
        return true;
    }

    public static String changeString(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ',') {
                sb.replace(i, i + 1, ".");
            }
            if (sb.charAt(i) == ' ') {
                sb.deleteCharAt(i);
                i--;
            }
        }
        return sb.toString();
    }

    public static String changeResult(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        int start = (sb.indexOf(".") == -1 && sb.indexOf(",") == -1) ? 0 : Math.max(sb.indexOf("."), sb.indexOf(",")) + 1;
        if (start != 0 && sb.charAt(start - 1) == ',') {
            sb.replace(start - 1, start, ".");
        }
        int curr;
        for (int i = start; i < sb.length(); i++) {
            if (sb.charAt(i) == ',') {
                sb.replace(i, i + 1, ".");
                i--;
            }
            curr = i;
            if (curr - start == 2) {
                sb.insert(curr + 1, ' ');
                start = i + 2;
            }
        }
        return sb.reverse().toString();
    }
}
