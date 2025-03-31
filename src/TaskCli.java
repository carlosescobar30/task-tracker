import java.io.File;

public class TaskCli {
    public static void main(String[] args) {
        File file = new File("tasks.json");
        TaskManagement taskManager = new TaskManager();
        int taskId;
        String errorMessage = "Comando no válido\nSi necesitas ayuda usa el comando help";
        String errorMessageEng = "Invalid command\nIf you need help, use the help command";
        if(args.length > 3) {
            System.out.println(errorMessage);
            return;
        }
        String option = args[0].trim().toLowerCase();
        switch (option){
            case "add":
                if (args.length != 2 || args[1].isBlank() || args[1].startsWith("\"") || args[1].endsWith("\"") ){
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                String taskDescription = args[1];
                taskManager.createTasks(taskDescription,file);
                return;
            case "update":
                if (args.length != 3 || args[2].isBlank() || args[2].startsWith("\"") || args[2].endsWith("\"")){
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                try{
                    taskId = Integer.parseInt(args[1].trim());
                }catch (NumberFormatException n){
                    System.out.println(n.getMessage());
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                String taskNewDescription = args[2].trim();
                taskManager.updateDescription(taskId,taskNewDescription,file);
                return;
            case "mark":
                if (args.length != 3 || args[2].isBlank()){
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                try{
                    taskId = Integer.parseInt(args[1].trim());
                }catch (NumberFormatException n){
                    System.out.println(n.getMessage());
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                String newStatus = args[2].trim().toLowerCase();
                taskManager.updateStatus(taskId,newStatus,file);
                return;

            case "delete":
                if (args.length != 2){
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                try {
                        taskId = Integer.parseInt(args[1].trim());
                }catch (NumberFormatException n){
                    System.out.println(n.getMessage());
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                taskManager.deleteTask(taskId,file);
                return;
            case "list":
                if (args.length < 2){
                    System.out.println(errorMessage);
                    System.out.println(errorMessageEng);
                    return;
                }
                if (!file.exists() || file.length() == 0){
                    System.out.println("Aun no has creado ninguna tarea");
                    System.out.println("You haven't created any tasks yet");
                    return;
                }
                String sortBy = "nothing";
                String taskStatus;
                if (args.length == 3 && !args[2].isBlank()){
                    sortBy = args[2].trim().toLowerCase();
                }
                switch (args[1].trim().toLowerCase()){
                    case "all", "done", "in-progress", "unstarted":
                        taskStatus = args[1].trim().toLowerCase();
                        break;
                    default:
                        System.out.println(errorMessage);
                        System.out.println(errorMessageEng);
                        return;
                }
                taskManager.listTasks(taskStatus,sortBy,file);
                return;

            case "help":
                System.out.println("(Español)");
                System.out.println("Task Cli tiene 5 comandos principales.\n");
                System.out.println("add: agrega una nueva tarea al programa.");
                System.out.println("uso: java TaskCli add \"<descripcion de la tarea>\"\n");
                System.out.println("update: actualiza la descripcion de una tarea.");
                System.out.println("uso: java TaskCli update <id de la tarea> \"<nueva descripcion de la tarea>\"\n");
                System.out.println("mark: marca el estado de una tarea.");
                System.out.println("uso: java TaskCli mark <id de la tarea> <nuevo estado de la tarea>*");
                System.out.println("*Las tareas por defecto son marcadas como unstarted.\n*Las entradas válidas para actualizar el estado de una tarea son: done e in-progress.\n");
                System.out.println("list: lista las tareas deseadas.");
                System.out.println("uso: java taskcli list <estado de las tareas>* <orden del listado>**");
                System.out.println("*Puedes listar las tareas por estado (unstarted, in-progress o done) y también listar todas las tareas usando el comando all.");
                System.out.println("**Este campo no es obligatorio, ya que por defecto las listas se ordenan por el id.\n**Pero si quieres organizar por fecha de creación, puedes usar la flag -sortcd.\n**O si deseas organizar por fecha de la última actualización, puedes usar la flag -sortud.\n");
                System.out.println("Si quieres volver a ver este texto de ayuda, puedes usar el comando help siempre que lo necesites.\n\n");
                System.out.println("(English)");
                System.out.println("Task Cli has 5 main commands.\n");
                System.out.println("add: adds a new task to the program.");
                System.out.println("usage: java TaskCli add \"<task description>\"\n");
                System.out.println("update: updates the description of a task.");
                System.out.println("usage: java TaskCli update <task id> \"<new task description>\"\n");
                System.out.println("mark: marks the status of a task.");
                System.out.println("usage: java TaskCli mark <task id> <new task status>*");
                System.out.println("*By default, tasks are marked as unstarted.\n*Valid inputs to update the status of a task are: done and in-progress.\n");
                System.out.println("list: lists the desired tasks.");
                System.out.println("usage: java taskcli list <task status>* <list order>**");
                System.out.println("*You can list tasks by status (unstarted, in-progress, or done) and also list all tasks using the all command.");
                System.out.println("**This field is not mandatory, as by default, lists are sorted by id.\n**But if you want to organize by creation date, you can use the flag -sortcd.\n**Or if you want to organize by last update date, you can use the flag -sortud.\n");
                System.out.println("If you want to see this help text again, you can use the help command whenever you need it.");
                break;
            default:
                System.out.println(errorMessage);
                System.out.println(errorMessageEng);
        }

    }
}