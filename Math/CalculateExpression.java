package Math;

/**
 * Created by Administrator on 2017/1/24.
 */
public class CalculateExpression {
    /**
     * Deal with + - * / ^
     */
    public static double parse(String expression) {
        // your code here
        double result = 0.0;
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }
        //Get rid of minus and split the string by the operator plus
        String[] substringByPlus = expression.replace("-", "+-").split("\\+");

        for (String subByPlus : substringByPlus) {
            //split by the multiple
            String[] substringByMultiple = subByPlus.split("\\*");
            double multiplResult = 1.0;
            for (String subByMultiple : substringByMultiple) {
                if (subByMultiple.contains("/")) {
                    //Split by the division symbol
                    String[] substringByDiv = subByMultiple.split("\\/");

                    //Get the result of power series and replace the substring
                    for (int i = 0; i < substringByDiv.length; i++) {
                        if (substringByDiv[i].contains("^")) {
                            String[] substringByPower = substringByDiv[i].split("\\^");
                            double powerResult = Math.pow(Double.parseDouble(substringByPower[0]),
                                    Double.parseDouble(substringByPower[1]));//e.g.2^3^2 not considered
                            substringByDiv[i] = Double.toString(powerResult);
                        }
                    }
                    double divident = Double.parseDouble(substringByDiv[0]);
                    for (int i = 1; i < substringByDiv.length; i++) {
                        divident /= Double.parseDouble(substringByDiv[i]);
                    }
                    multiplResult *= divident;
                } else {
                    multiplResult *= Double.parseDouble(subByMultiple);
                }
            }
            result += multiplResult;
        }
        return result;

    }

    public static void main(String[] args) {

        String exp = "-1.2/6+7.5 + 2^3/4";
        System.out.println(parse(exp));
    }

    /**
     * From web.Deal with () sqrt(). etc..
     */
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
