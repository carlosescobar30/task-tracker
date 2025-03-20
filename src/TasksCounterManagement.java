import java.io.File;

public class TasksCounterManagement {
    public static StringBuilder updateTasksCounter (StringBuilder builder){
        int indexValueStart = builder.indexOf("\"allTasks\":") + 11;
        int indexValueEnd = builder.indexOf("}", builder.indexOf("\"allTasks\":"));
        builder.replace(indexValueStart,indexValueEnd,Integer.toString(Task.getTasksCounter()));
        builder.insert(indexValueEnd,"\n ");

        return builder;
    }
}
