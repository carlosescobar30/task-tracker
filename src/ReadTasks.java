import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTasks {
    public static String readTasks(File file){
        StringBuilder tasksBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                tasksBuilder.append(line).append("\n");
            }
        }catch (IOException e){
            System.out.println("Error Fatal " + e.getMessage());
            e.printStackTrace();
        }

        return tasksBuilder.toString();
    }
}
