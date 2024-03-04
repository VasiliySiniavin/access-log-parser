import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();
        int sum = firstNumber + secondNumber;
        int diff = firstNumber - secondNumber;
        int multi = firstNumber * secondNumber;
        double quotient = (double) firstNumber % secondNumber;

        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + diff);
        System.out.println("Произведение: " + multi);
        System.out.println("Частное: " + quotient);

        int count = 0;
        while (true) {
            System.out.println("Укажите путь к файлу, который программма будет парсить: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (!fileExists || isDirectory) {
                System.out.println("Указанный файл не существует или " +
                        "указанный путь является путём к папке, а не к файлу");
                continue;
            } else {
                System.out.println("Путь указан верно");
                count++;
            }
            System.out.println("Это файл номер " + count);
        }
    }
}