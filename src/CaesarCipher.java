public class CaesarCipher {

    // Метод шифрования текста с заданным сдвигом
    public static String encrypt(String text, int shift) {
        return process(text, shift);
    }

    // Метод расшифровки текста с заданным сдвигом
    public static String decrypt(String text, int shift) {
        return process(text, -shift); // Расшифровка - это тот же шифр, но в обратную сторону
    }

    // Метод для попытки всех возможных сдвигов (для дешифровки без ввода сдвига)
    public static void tryAllShifts(String text) {
        System.out.println("Trying all possible shifts:");
        for (int i = 1; i < 33; i++) { // 32 буквы в кириллице
            String result = decrypt(text, i);
            System.out.printf("Shift %2d: %s%n", i, result); // 2 символа ширины, выравнивание по правому краю, строка, новая строка
        }
    }

    // Универсальный метод для шифрования и дешифрования
    private static String process(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            result.append(shiftChar(c, shift)); // Обрабатываем каждый символ
        }

        return result.toString();
    }

    // Метод сдвига одного символа
    private static char shiftChar(char c, int shift) {
        // Обработка английских символов
        if (Character.isLetter(c)) {
            if (c >= 'A' && c <= 'Z') {
                return (char) ((c - 'A' + shift + 26) % 26 + 'A'); // для заглавных
            } else if (c >= 'a' && c <= 'z') {
                return (char) ((c - 'a' + shift + 26) % 26 + 'a'); // для строчных
            }

            // Обработка русских символов
            else if (c >= 'А' && c <= 'Я') {
                return (char) ((c - 'А' + shift + 32) % 32 + 'А');
            } else if (c >= 'а' && c <= 'я') {
                return (char) ((c - 'а' + shift + 32) % 32 + 'а');
            }
        }

        // Неалфавитные символы возвращаем без изменений
        return c;
    }
}