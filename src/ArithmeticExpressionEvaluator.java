public class ArithmeticExpressionEvaluator {

    // Основной метод вычисления выражения
    public static double calculate(String expr) {
        return parseExpression(new SymbolStream(expr));
    }

    // Метод разбора выражений с + и -
    private static double parseExpression(SymbolStream ts) {
        double value = parseTerm(ts);

        while (ts.hasNext()) {
            char op = ts.peek();
            if (op == '+' || op == '-') {
                ts.next();
                double next = parseTerm(ts);
                value = (op == '+') ? value + next : value - next;
            } else break;
        }

        return value;
    }

    // Метод разбора * и /
    private static double parseTerm(SymbolStream ts) {
        double value = parseFactor(ts);

        while (ts.hasNext()) {
            char op = ts.peek();
            if (op == '*' || op == '/') {
                ts.next();
                double next = parseFactor(ts);
                if (op == '*') value *= next;
                else {
                    if (next == 0) throw new ArithmeticException("Деление на ноль");
                    value /= next;
                }
            } else break;
        }

        return value;
    }

    // Метод разбора скобок, чисел и унарного минуса
    private static double parseFactor(SymbolStream ss) {
        if (!ss.hasNext()) throw new IllegalArgumentException("Ожидалось число");

        char ch = ss.peek();

        if (ch == '(') {
            ss.next();
            double val = parseExpression(ss);
            if (!ss.hasNext() || ss.next() != ')') throw new IllegalArgumentException("Ожидалась закрывающая скобка");
            return val;
        }

        if (ch == '-') {
            ss.next();
            return -parseFactor(ss); // унарный минус
        }

        return ss.parseNumber(); // обычное число
    }

    // Класс выделения значений (разбиение строки на элементы)
    private static class SymbolStream {
        private final String input;
        private int pos = 0;

        SymbolStream(String input) {
            this.input = input.replaceAll("\\s+", ""); // удаляем пробелы
        }

        boolean hasNext() {
            return pos < input.length();
        }

        char peek() {
            return input.charAt(pos);
        }

        char next() {
            return input.charAt(pos++);
        }

        double parseNumber() {
            int start = pos;
            while (hasNext() && (Character.isDigit(peek()) || peek() == '.')) {
                next();
            }

            String numStr = input.substring(start, pos);
            if (numStr.isEmpty()) throw new IllegalArgumentException("Ожидалось число");
            return Double.parseDouble(numStr);
        }
    }
}