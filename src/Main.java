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
        actions:switch (option){
            case "1":
                System.out.println("por favor, ingrese la nueva tarea");
                String taskDescription = scanner.nextLine();
                if (!file.exists()){
                    CreateTasks.createFirstTasks(taskDescription);
                }else {
                    CreateTasks.createNextTask(taskDescription,file);
                }
                System.out.println("Tarea creada con exito");
                break;

            case "2":
                System.out.println("¿que desea actualizar?");
                System.out.println("1.descripcion");
                System.out.println("2.estado");
                String optionUpdate = scanner.nextLine();
                System.out.println("por favor ingrese el id del elemento que desea modificar");
                String taskIdString = scanner.nextLine();
                Integer taskId = Integer.parseInt(taskIdString);
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
                    case "3":

                }

        }

    }
}