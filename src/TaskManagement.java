import java.io.File;

public interface TaskManagement {

    void createTasks(String taskName, File file);

    void updateDescription(Integer taskId, String description,File file);

    void updateStatus(Integer taskId, String status,File file);

    void deleteTask (Integer taskId, File file);

}
