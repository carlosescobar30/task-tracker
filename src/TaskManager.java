import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class TaskManager {

    /*public static void deleteTask(Integer taskId, File file){
        HashMap<Integer, Task> tasks = new HashMap<>(Formatters.formatToTasks(file));
        tasks.remove(taskId);
        StringBuilder builder = new StringBuilder(ReadTasks.readTasks(file));
        try (FileWriter writer = new FileWriter(file)){
            for (Task task : tasks.values()){
                writer.write(builder.toString());
            }
        }catch (IOException e){
            System.out.println("Error Fatal" + e.getMessage());
        }
        int indexId = builder.indexOf("\"id\":");
        if (indexId == -1){
            file.delete();
        }

    }*/

    public static void updateDescription(Task task, String description,File file){
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

    public static void updateStatus(Task task, String status,File file){
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

    public static void updateCounterSB(StringBuilder builder){
        int indexValueStart = builder.indexOf("\"allTasks\":") + 11;
        int indexValueEnd = builder.indexOf("}", builder.indexOf("\"allTasks\":"));
        builder.replace(indexValueStart,indexValueEnd,Integer.toString(Task.getTasksCounter()) + "\n ");
    }

    public static Integer captureTasksCounter(String json){
        return Integer.parseInt(json.substring(
                json.indexOf("\"allTasks\":") + 11,
                json.indexOf("}", json.indexOf("\"allTasks\":"))).trim());
    }

    public static void updateCounterAttr(File file){
        String jsonString = ReadTasks.readTasks(file);
        int capturedCounterTasks = TaskManager.captureTasksCounter(jsonString);
        Task.setTasksCounter(capturedCounterTasks);
    }


}
