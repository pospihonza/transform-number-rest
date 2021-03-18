package cz.pospisil.transformnumberrest.domain;

public class TransformedNumber {

    private final String number;

    public TransformedNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return transformNumber(number);
    }

    private String transformNumber(String number) {

        String s = number;
        int evenSum;

        s = shift123(s);       // '626' -> '662'
        s = multiply89By2(s);  // '696' -> '6186'
        s = clean7(s);         // '676' -> '66'

        evenSum = countEvenDigits(s);

        // parse as Double to make division in floating-point context
        return String.valueOf(Math.round(Double.parseDouble(s) / evenSum));
    }

    private String shift123(String num) {
        String s = num;

        for (int i = s.length() - 2; i >= 0; i--) {
            // is 0 or 1 or 2 or 3?
            if ("0123".indexOf(s.charAt(i)) != -1) {
                // shift respective char one place to the right
                s = shiftCharAt(s, i);
            }
        }
        return s;
    }

    private String shiftCharAt(String s, int i) {
        StringBuilder sb = new StringBuilder();

        sb.append(s.substring(0, i));       // part before shifted char
        sb.append(s.charAt(i + 1));         // char after shifted char
        sb.append(s.charAt(i));             // shifted char
        if (i < s.length() - 2) {
            sb.append(s.substring(i + 2));  // part after two swapped chars
        }
        return sb.toString();
    }

    private String multiply89By2(String num) {
        String s = num;

        for (int i = 0; i < s.length(); i++) {
            // is 8 or 9?
            if ("89".indexOf(s.charAt(i)) != -1) {
                // multiply digit by 2
                s = multiplyDigitAtBy2(s, i);
                i++;  // product of 8*2 or 9*2 has two chars, skip 2nd one
            }
        }
        return s;
    }

    private String multiplyDigitAtBy2(String s, int i) {
        StringBuilder sb = new StringBuilder();

        sb.append(s.substring(0, i));        // part before multiplied digit
        sb.append((s.charAt(i) - '0') * 2);  // digit multiplied by 2
        if (i < s.length() - 1) {
            sb.append(s.substring(i + 1));   // part after multiplied digit
        }
        return sb.toString();
    }

    private String clean7(String num) {
        String s = num;

        for (int i = 0; i < s.length(); i++) {
            // is 7?
            if (s.charAt(i) == '7') {
                // remove digit 7
                StringBuilder sb = new StringBuilder();
                sb.append(s.substring(0, i));       // part before digit 7
                if (i < s.length() - 1) {
                    sb.append(s.substring(i + 1));  // part after digit 7
                }
                s = sb.toString();
                i--;  // next char in for cycle is earlier place of 7
            }
        }
        return s;
    }

    private int countEvenDigits(String num) {
        int cnt = 0;

        for (char c : num.toCharArray()) {
            // is even digit?
            if (c % 2 == 0) {
                cnt++;
            }
        }
        return cnt;
    }

}
