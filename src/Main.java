import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("tasks.json");
        System.out.println("que desea hacer?");
        System.out.println("1.Crear tarea");
        System.out.println("2.actualizar tarea");
        System.out.println("3.eliminar tarea");
        String option = scanner.nextLine();
        int taskId;
        String taskIdString;
        actions:switch (option){
            case "1":
                System.out.println("por favor, ingrese la nueva tarea");
                String taskDescription = scanner.nextLine();
                if (!file.exists() || file.length() == 0){
                    CreateTasks.createFirstTasks(taskDescription);
                }else {
                    CreateTasks.createNextTask(taskDescription,file);
                }
                System.out.println("Tarea creada con exito");
                break;

            case "2":
                System.out.println("por favor ingrese el id del elemento que desea modificar");
                taskIdString = scanner.nextLine();
                taskId = Integer.parseInt(taskIdString);
                System.out.println("¿que desea actualizar?");
                System.out.println("1.descripcion");
                System.out.println("2.estado");
                String optionUpdate = scanner.nextLine();
                switch (optionUpdate){
                    case "1":
                        System.out.println("por favor ingrese la nueva descripcion");
                        String newTaskDescription = scanner.nextLine();
                        TaskManager.updateDescription(taskId,newTaskDescription,file);
                        System.out.println("Descripcion actualizada con exito");
                        break actions;

                    case "2":
                        System.out.println("por favor ingrese el nuevo estado");
                        String newTaskStatus = scanner.nextLine();
                        TaskManager.updateStatus(taskId,newTaskStatus,file);
                        System.out.println("Estado actualizada con exito");
                        break actions;

                    default:
                        System.out.println("Opcion no valida");
                        break actions;
                        }

            case "3":
                System.out.println("por favor ingrese el id de la tarea que quiere eliminar");
                taskIdString = scanner.nextLine();
                taskId = Integer.parseInt(taskIdString);
                TaskManager.deleteTask(taskId,file);
                System.out.println("Tarea eliminada con exito");
                break;
        }
    }
}