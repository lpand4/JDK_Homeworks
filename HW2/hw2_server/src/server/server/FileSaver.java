package server.server;


import java.io.FileReader;
import java.io.FileWriter;

/**
 * Работает с файлом для сохранения и загрузки
 */
public class FileSaver implements Repository {
    public static final String LOG_PATH = "src/server/log.txt";


    @Override
    public void saveLog(String log) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(log);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
