import java.io.*;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class TaskManager implements TaskManagement {
    String errorMesagge = "comando no valido\nsi necesitas ayuda usa el comando help";
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
                System.out.println("archivo creado");
            } catch (IOException e) {
                System.out.println("Error Fatal " + e.getMessage());
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
                System.out.println("Error Fatal" + e.getMessage());
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
            System.out.println("Error Fatal " + e.getMessage());
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
                System.out.println("Error Fatal" + e.getMessage());
            }
        }else {
            System.out.println("Tarea no encontrada");
            System.out.println(errorMesagge);
        }
    }

    @Override
    public void updateStatus(Integer taskId, String status,File file){
        if (!status.equals("-done") && !status.equals("-in-progress")){
            System.out.println(errorMesagge);
            return;
        }
        status = status.replace("-"," ").trim();
        TreeMap<Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
        Task task = taskTreeMap.get(taskId);
        task.setDescription(status);
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
                System.out.println("Error Fatal" + e.getMessage());
            }
        }else {
            System.out.println("Tarea no encontrada");
            System.out.println(errorMesagge);
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
        TreeMap <Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
        if (!taskTreeMap.containsKey(taskId)){
            System.out.println("Tarea no encontrada");
            System.out.println(errorMesagge);
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
            System.out.println("Error Fatal" + e.getMessage());
        }
    }
}
