import java.io.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TaskManager implements TaskManagement {
    String errorMessage = "Comando no valido\nSi necesitas ayuda usa el comando help";
    String errorMessageEng = "Invalid command\nIf you need help, use the help command";
    //Create

    @Override
    public void createTasks(String taskName, File file) {
        if (!file.exists() || file.length() == 0) {
            Task task = new Task(taskName);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("{\n");
                writer.write("  \"tasks\":[\n");
                writer.write(Formatters.taskToStringBuilder(task).toString());
                writer.write("\n ]\n}");
            } catch (IOException e) {
                System.out.println("Fatal Error" + e.getMessage());
                e.printStackTrace();
            }
        }else {
            TaskManager.updateLastId(file);
            Task task = new Task(taskName);
            StringBuilder builder = new StringBuilder(TaskManager.readTasks(file));
            int indexEndJson = builder.lastIndexOf("]");
            builder.insert((indexEndJson - 2),(",\n"));
            builder.insert(indexEndJson, Formatters.taskToStringBuilder(task));
            try(FileWriter writer = new FileWriter(file)){
                writer.write(builder.toString());
            }catch (IOException e){
                System.out.println("Fatal Error" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    //Read

    public static String readTasks(File file){
        StringBuilder tasksBuilder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null){
                tasksBuilder.append(line).append("\n");
            }
        }catch (IOException e){
            System.out.println("Fatal Error" + e.getMessage());
            e.printStackTrace();
        }
        return tasksBuilder.toString();
    }


    //Update

    @Override
    public void updateDescription(Integer taskId, String description,File file){
        TreeMap<Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
        Task task = taskTreeMap.get(taskId);
        task.setDescription(description);
        StringBuilder builder = new StringBuilder(TaskManager.readTasks(file));
        int indexId = builder.indexOf("\"id\":" + task.getId());
        if (indexId != -1){
            int indexValueStart = builder.indexOf("\"description\":",indexId) + 15;
            int indexValueEnd = builder.indexOf("\"", indexValueStart);
            builder.replace(indexValueStart, indexValueEnd,description);
            int indexDateStart = builder.indexOf("\"last update date\":",indexId) + 20;
            int indexDateEnd = builder.indexOf("\"", indexDateStart);
            builder.replace(indexDateStart, indexDateEnd,LocalDateTime.now().toString());
            try (FileWriter writer = new FileWriter(file)){
                writer.write(builder.toString());
            }catch (IOException e){
                System.out.println("Fatal Error" + e.getMessage());
            }
        }else {
            System.out.println("Tarea no encontrada");
            System.out.println("Task not found\n");
            System.out.println(errorMessage);
            System.out.println(errorMessageEng);
        }
    }

    @Override
    public void updateStatus(Integer taskId, String status,File file){
        if (!status.equals("done") && !status.equals("in-progress")){
            System.out.println(errorMessage);
            System.out.println(errorMessageEng);
            return;
        }
        status = status.trim();
        TreeMap<Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
        Task task = taskTreeMap.get(taskId);
        task.setStatus(status);
        StringBuilder builder = new StringBuilder(TaskManager.readTasks(file));
        int indexId = builder.indexOf("\"id\":" + task.getId());
        if (indexId != -1){
            int indexValueStart = builder.indexOf("\"status\":",indexId) + 10;
            int indexValueEnd = builder.indexOf("\"", indexValueStart);
            builder.replace(indexValueStart, indexValueEnd,status);
            int indexDateStart = builder.indexOf("\"last update date\":",indexId) + 20;
            int indexDateEnd = builder.indexOf("\"", indexDateStart);
            builder.replace(indexDateStart, indexDateEnd,LocalDateTime.now().toString());
            try (FileWriter writer = new FileWriter(file)){
                writer.write(builder.toString());
            }catch (IOException e){
                System.out.println("Fatal Error" + e.getMessage());
            }
        }else {
            System.out.println("Tarea no encontrada");
            System.out.println("Task not found\n");
            System.out.println(errorMessage);
            System.out.println(errorMessageEng);
        }
    }

    public static void updateLastId(File file){
        TreeMap<Integer,Task> tasksTree = Formatters.fileToTreeMap(file);
        Task lastTask = tasksTree.lastEntry().getValue();
        Task.setLastId(lastTask.getId());
    }

    //Delete

    @Override
    public void deleteTask (Integer taskId, File file) {
        if (!file.exists() || file.length() == 0){
            System.out.println("Aun no has creado ninguna tarea");
            System.out.println("You haven't created any tasks yet");
            return;
        }
        TreeMap <Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
        if (!taskTreeMap.containsKey(taskId)){
            System.out.println("Tarea no encontrada");
            System.out.println("Task not found\n");
            System.out.println(errorMessage);
            System.out.println(errorMessageEng);
            return;
        }
        taskTreeMap.remove(taskId);
        StringBuilder updatedTasksSB = new StringBuilder();
        if(!taskTreeMap.isEmpty()){
            updatedTasksSB = new StringBuilder(Formatters.treeMapToStringBuilder(taskTreeMap,file));
        }
        try (FileWriter writer = new FileWriter(file)){
            writer.write(updatedTasksSB.toString());
        }catch (IOException e){
            System.out.println("Fatal Error" + e.getMessage());
        }
    }

    //List
    public void listTasks(String taskStatus, String sortBy, File file){
        if (!file.exists() || file.length() == 0){
            System.out.println("No hay ninguna tarea que mostrar");
            System.out.println("There are no tasks to display");
            return;
        }
        TreeMap<Integer,Task> tasks = Formatters.fileToTreeMap(file);
        if(!taskStatus.equals("all")){
            tasks.entrySet().removeIf(entry -> !entry.getValue().getStatus().equals(taskStatus));
        }

        switch (sortBy){
            case "-sortcd":
                tasks.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.comparing(Task::getCreatedAt).reversed()))
                        .forEach(entry -> System.out.println(entry.getValue().toString()));
                break;
            case "-sortud":
                tasks.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.comparing(Task::getUpdateAt).reversed()))
                        .forEach(entry -> System.out.println(entry.getValue().toString()));
                break;
            default :
                for (Task task : tasks.values()){
                    System.out.println(task.toString());
                }
                break;
        }
    }
}
