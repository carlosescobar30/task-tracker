import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.TreeMap;

public class Formatters {



    public static StringBuilder TaskToStringBuilder(Task task) {
        StringBuilder formatter = new StringBuilder();
        formatter
                .append("    {\n")
                .append("     \"id\":")
                .append(task.getId())
                .append(",\n")
                .append("     \"description\":\"")
                .append(task.getDescription())
                .append("\",\n")
                .append("     \"status\":\"")
                .append(task.getStatus())
                .append("\",\n")
                .append("     \"creation date\":\"")
                .append(task.getCreatedAt().format(Task.getDateFormat()))
                .append("\",\n")
                .append("     \"last update date\":\"")
                .append(task.getUpdateAt().format(Task.getDateFormat()))
                .append("\"\n")
                .append("    }");
        return formatter;
    }


    public static Task StringToTask(String json){

        int id = Integer.parseInt(json.substring(
                json.indexOf("\"id\":")+5,
                json.indexOf(",", json.indexOf("\"id\":"))));

        String description = json.substring(
                json.indexOf("\"description\":")+15,
                json.indexOf("\",", json.indexOf("\"description\":")))
                .replace("\"","");

        String status = json.substring(
                        json.indexOf("\"status\":")+9,
                        json.indexOf(",", json.indexOf("\"status\":")))
                .replace("\"","");

        LocalDateTime createdAt = LocalDateTime.parse(json.substring(
                json.indexOf("\"creation date\":")+16,
                json.indexOf(",", json.indexOf("\"creation date\":"))).replace("\"","").trim(),Task
                .getDateFormat());

        LocalDateTime updateAt = LocalDateTime.parse(json.substring(
                json.indexOf("\"last update date\":")+19,
                json.indexOf("}", json.indexOf("\"last update date\":"))).replace("\"","").trim(),Task.getDateFormat());


        return new Task(id,description,status,createdAt,updateAt);
    }

    public static TreeMap<Integer,Task> fileToTreeMap(File file) {
        String json = ReadTasks.readTasks(file);
        TreeMap<Integer,Task> tasksTreeMap = new TreeMap<>();
        int tasksStart = json.indexOf("\"tasks\":") + 9;
        int tasksEnd = json.indexOf("]",tasksStart);
        String tasksString = json.substring(tasksStart,tasksEnd);
        String[] arrayTasksString = tasksString.split("},");
        for (String taskStringInd : arrayTasksString){
            if (!taskStringInd.trim().startsWith("{")){
                taskStringInd = "{" + taskStringInd;
            }
            if (!taskStringInd.trim().endsWith("}")){
                taskStringInd = taskStringInd + "}";
            }
            tasksTreeMap.put(StringToTask(taskStringInd).getId(), StringToTask(taskStringInd));
        }


        return  tasksTreeMap;
    }

}
