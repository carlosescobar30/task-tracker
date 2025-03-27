import java.io.File;

public interface TaskManagement {

    public void createTasks(String taskName, File file);

    public void updateDescription(Integer taskId, String description,File file);

    public void updateStatus(Integer taskId, String status,File file);

    public void deleteTask (Integer taskId, File file);

}
