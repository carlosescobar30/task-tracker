import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("tasks.json");
        HashMap<Integer,Task> tasksHashMap = new HashMap<>(Formatters.formatToTasks(file));
        System.out.println("Ingrese el id de la tarea que quiere eliminar:");
        Integer taskId = scanner.nextInt();
        /*TaskManager.deleteTask(taskId,file);*/

    }
}