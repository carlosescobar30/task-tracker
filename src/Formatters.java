import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Formatters {



    public static StringBuilder formatToJson(Task task) {
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


    public static Task formatToObject(String json){

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

    public static HashMap<Integer,Task> formatToTasks(File file) {
        String json = ReadTasks.readTasks(file);
        HashMap<Integer,Task> tasksHashMap = new HashMap<>();
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
            tasksHashMap.put(formatToObject(taskStringInd).getId(),formatToObject(taskStringInd));
        }


        return  tasksHashMap;
    }

}
