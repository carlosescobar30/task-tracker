import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManagement taskManagement = new TaskManager();
        File file = new File("tasks.json");
        System.out.println("que desea hacer?");
        System.out.println("1.Crear tarea");
        System.out.println("2.actualizar descripcion de la tarea");
        System.out.println("3.actualizar estado de la tarea");
        System.out.println("4.eliminar tarea");
        String option = scanner.nextLine();
        int taskId;
        String taskIdString;
        actions:switch (option){
            case "1":
                System.out.println("por favor, ingrese la nueva tarea");
                String taskDescription = scanner.nextLine();
                taskManagement.createTasks(taskDescription,file);
                System.out.println("Tarea creada con exito");
                break;

            case "2":
                System.out.println("por favor ingrese el id de la tarea que desea actualizar la descripcion");
                taskIdString = scanner.nextLine();
                taskId = Integer.parseInt(taskIdString);
                System.out.println("por favor ingrese la nueva descripcion");
                String newTaskDescription = scanner.nextLine();
                taskManagement.updateDescription(taskId,newTaskDescription,file);
                System.out.println("Descripcion actualizada con exito");
                break;

            case "3":
                System.out.println("por favor ingrese el id de la tarea que desea actualizar el estado");
                taskIdString = scanner.nextLine();
                taskId = Integer.parseInt(taskIdString);
                System.out.println("por favor ingrese el nuevo estado");
                String newTaskStatus = scanner.nextLine();
                taskManagement.updateStatus(taskId,newTaskStatus,file);
                System.out.println("Estado actualizada con exito");
                break;
            case "4":
                System.out.println("por favor ingrese el id de la tarea que quiere eliminar");
                taskIdString = scanner.nextLine();
                taskId = Integer.parseInt(taskIdString);
                taskManagement.deleteTask(taskId,file);
                System.out.println("Tarea eliminada con exito");
                break;
        }
    }
}