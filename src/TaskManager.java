import java.io.File;

public class TaskManager {

    public void updateDescription(Task task, String description){
        task.setDescription(description);
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
