import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
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
            int lines = 0;
            int max = 0;
            int countYB = 0;
            int countGB = 0;
            int countM = 0;

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {

                    String[] userAgent = line.split("[\"]");
                    for (String firstBrackets : userAgent) {
                        if (firstBrackets.contains("Mozilla")) {
                            countM++;
                        }
                        String[] parts = firstBrackets.split(";");
                        if (parts.length >= 2) {
                            String fragment = parts[1];
                            String[] parts1 = fragment.trim().split("\\s*(\\s|,|!|/|\\.)\\s*");
                            String total = parts1[0];
                            System.out.println(total);
                            if (total.equals("YandexBot")) {
                                countYB++;
                            }
                            if (total.equals("Googlebot"))
                                countGB++;
                        }
                    }
                }
                System.out.println("Количество запросов от YandexBot: " + countYB + " или " + percentage(countYB, lines) + "%");
                System.out.println("Количество запросов от GoogleBot: " + countGB + " или " + percentage(countGB, lines) + "%");
                System.out.println("Количество запросов: " + countM);
                System.out.println("Общее количество строк в файле: " + lines);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (max > 1024) {
                throw new exceedingCharacters("в файле встретилась строка длиннее 1024 символов");
            }
        }
        }

    public static double percentage(double x, double y) {
        return Math.round((x / y*100));

    }
}
enum HttpMethod {
    GET, POST, PUT, PATCH, DELETE;
}
class exceedingCharacters extends RuntimeException {
    public exceedingCharacters(String message) {
        super(message);
    }
}



