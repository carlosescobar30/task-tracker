import java.util.ArrayList;
import java.util.List;

public class FormatConverters {

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
                json.indexOf("\",", json.indexOf("\"description\":")));

        String status = json.substring(
                        json.indexOf("\"status\":")+9,
                        json.indexOf("}", json.indexOf("\"status\":")))
                .replace("\"","");

        return new Task(id,description,status);
    }

    public static List<Task> parseToTasks(String json) {
        List<Task> tasksList = new ArrayList<>();
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
            tasksList.add(formatToObject(taskStringInd));
        }


        return  tasksList;
    }

}
