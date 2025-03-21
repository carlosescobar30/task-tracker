import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("tasks.json");
        System.out.println("Por favor, ingrese una nueva tarea");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!file.exists()){
            CreateTasks.createFirstTasks(input);
        }else {
            TaskManager.updateCounterAttr(file);
            CreateTasks.createNextTask(input,file);
        }
    }
}