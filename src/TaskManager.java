import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class TaskManager {

    public static void updateLastId(File file){
        TreeMap<Integer,Task> tasksTree = Formatters.fileToTreeMap(file);
        Task lastTask = tasksTree.lastEntry().getValue();
        Task.setLastId(lastTask.getId());
    }

    public static void updateDescription(Integer taskId, String description,File file){
        TreeMap<Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
        Task task = taskTreeMap.get(taskId);
        task.setDescription(description);
        StringBuilder builder = new StringBuilder(ReadTasks.readTasks(file));
        int indexId = builder.indexOf("\"id\":" + task.getId());
        if (indexId != -1){
            int indexValueStart = builder.indexOf("\"description\":",indexId) + 15;
            int indexValueEnd = builder.indexOf("\"", indexValueStart);
            builder.replace(indexValueStart, indexValueEnd,description);
            try (FileWriter writer = new FileWriter(file)){
                writer.write(builder.toString());
            }catch (IOException e){
                System.out.println("Error Fatal" + e.getMessage());
            }
        }else {
            System.out.println("Tarea no encontrada");
        }
    }

    public static void updateStatus(Integer taskId, String status,File file){
        TreeMap<Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
        Task task = taskTreeMap.get(taskId);
        task.setDescription(status);
        StringBuilder builder = new StringBuilder(ReadTasks.readTasks(file));
        int indexId = builder.indexOf("\"id\":" + task.getId());
        if (indexId != -1){
            int indexValueStart = builder.indexOf("\"status\":",indexId) + 10;
            int indexValueEnd = builder.indexOf("\"", indexValueStart);
            builder.replace(indexValueStart, indexValueEnd,status);
            try (FileWriter writer = new FileWriter(file)){
                writer.write(builder.toString());
            }catch (IOException e){
                System.out.println("Error Fatal" + e.getMessage());
            }
        }else {
            System.out.println("Tarea no encontrada");
        }
    }

    public static void deleteTask (Integer taskId, File file) {
        TreeMap <Integer,Task> taskTreeMap = new TreeMap<>(Formatters.fileToTreeMap(file));
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
