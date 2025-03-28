import java.io.File;
import java.io.IOException;
import java.util.*;

public class TaskTracker {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
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
        }*/
        File file = new File("tasks.json");
        TaskManagement taskManager = new TaskManager();
        int taskId;
        String errorMesagge = "comando no valido\nsi necesitas ayuda usa el comando help";
        if(args.length > 4) {
            System.out.println(errorMesagge);
            return;
        }
        String option = args[0].trim().toLowerCase();
        switch (option){
            case "add":
                if (args.length == 2 || args[1].isBlank() || args[1].startsWith("\"") || args[1].endsWith("\"") ){
                    System.out.println(errorMesagge);
                    return;
                }
                String taskDescription = args[1].substring(1,args[1].length() - 1);
                taskManager.createTasks(taskDescription,file);
                return;
            case "update":
                if (args.length != 3 || args[2].isBlank() || args[2].startsWith("\"") || args[2].endsWith("\"")){
                    System.out.println(errorMesagge);
                    return;
                }
                try{
                    taskId = Integer.parseInt(args[1].trim());
                }catch (NumberFormatException n){
                    System.out.println(n.getMessage());
                    System.out.println(errorMesagge);
                    return;
                }
                String taskNewDescription = args[2].substring(1,args[2].length() - 1).trim();
                taskManager.updateDescription(taskId,taskNewDescription,file);
                return;
            case "mark":
                if (args.length != 3 || args[2].isBlank()){
                    System.out.println(errorMesagge);
                    return;
                }
                try{
                    taskId = Integer.parseInt(args[1].trim());
                }catch (NumberFormatException n){
                    System.out.println(n.getMessage());
                    System.out.println(errorMesagge);
                    return;
                }
                String newStatus = args[2].trim().toLowerCase();
                taskManager.updateStatus(taskId,newStatus,file);
                return;
            case "list":
                if (args.length < 2){


                }

            case "help":
        }

    }
}