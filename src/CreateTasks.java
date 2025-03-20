import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class CreateTasks {

    public static void createFirstTasks(String taskName) {
        Task task = new Task(taskName);
        StringBuilder taskSerial = FormatConverters.formatToJson(task);
        File file = new File("tasks.json");
        try (FileWriter writer = new FileWriter(file)) {
            Task.setTasksCounter(Task.getTasksCounter());
            writer.write("{\n");
            writer.write("  \"metadata\":{\n");
            writer.write("   \"allTasks\":");
            writer.write(Integer.toString(Task.getTasksCounter()));
            writer.write("\n },\n");
            writer.write("  \"tasks\":[\n");
            writer.write(taskSerial.toString());
            writer.write("\n ]\n}");

            System.out.println("archivo creado");


        } catch (IOException e) {
            System.out.println("Error Fatal " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static  void createNextTask (String taskName, File file){
        StringBuilder builder = new StringBuilder(ReadTasks.readTasks(file));
        TasksCounterManagement.updateTasksCounter(builder);
        int indexEndJson = builder.lastIndexOf("]");
        builder.insert((indexEndJson - 2),(",\n"));
        builder.insert(indexEndJson,taskSerial);
        try(FileWriter writer = new FileWriter(file)){
            writer.write(builder.toString());
        }catch (IOException e){
            System.out.println("Error Fatal" + e.getMessage());
        }
    }

}
