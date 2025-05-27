import java.util.Scanner;

//Код написан в рамках выполнения тестового задания.
//Некоторые решения реализованы с подсказкой.
//Я могу подробно объяснить, как и почему они работают.

public class MainApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Создаём сканер для чтения ввода с клавиатуры

        while (true) {
            // Главное меню
            System.out.println("\nWelcome to Gehtsoft Technical Assessment");
            System.out.println("Please choose an option:");
            System.out.println("1. Caesar Cipher Encryption");
            System.out.println("2. Caesar Cipher Decryption");
            System.out.println("3. Arithmetic Expression Evaluation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Шифрование Цезаря
                    System.out.print("Enter text to encrypt: ");
                    String encryptText = scanner.nextLine();

                    // Проверка и обработка ввода сдвига
                    int encryptShift;
                    while (true) {
                        System.out.print("Enter shift value: ");
                        String shiftInput = scanner.nextLine();
                        if (shiftInput.trim().isEmpty()) {
                            System.out.println("Shift value is required. Please enter a number.");
                        } else {
                            try {
                                encryptShift = Integer.parseInt(shiftInput.trim());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
                        }
                    }

                    String encrypted = CaesarCipher.encrypt(encryptText, encryptShift);
                    System.out.println("Result: " + encrypted);
                    break;

                case "2":
                    // Расшифровка Цезаря
                    System.out.print("Enter text to decrypt: ");
                    String decryptText = scanner.nextLine();

                    System.out.print("Enter shift value (or blank to guess): ");
                    String decryptShiftInput = scanner.nextLine();

                    if (decryptShiftInput.isEmpty()) {
                        CaesarCipher.tryAllShifts(decryptText);
                    } else {
                        try {
                            int decryptShift = Integer.parseInt(decryptShiftInput.trim());
                            String decrypted = CaesarCipher.decrypt(decryptText, decryptShift);
                            System.out.println("Result: " + decrypted);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid shift value. Please enter a valid integer.");
                        }
                    }
                    break;

                case "3":
                    // Калькулятор выражений
                    System.out.print("Enter expression (e.g. (2 + 3) * 4 - 1): ");
                    String expression = scanner.nextLine();

                    try {
                        double result = ArithmeticExpressionEvaluator.calculate(expression);
                        System.out.println("Result: " + result);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "4":
                    // Выход из программы
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Запрос на продолжение
            System.out.print("\nContinue? (y/n): ");
            String cont = scanner.nextLine();
            if (!cont.equalsIgnoreCase("y")) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}