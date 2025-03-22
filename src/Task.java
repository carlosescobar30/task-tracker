import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final int id;
    private static int tasksCounter = 0;
    private String description;
    private String status;
    private final static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
    private final LocalDateTime createdAt;
    private LocalDateTime updateAt;


    //BUILDERS

    public Task(String description) {
        this.id = ++tasksCounter;
        this.description = description;
        this.status = "unstarted";
        this.createdAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Task(Integer id, String description, String status, LocalDateTime createdAt, LocalDateTime updateAt){
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    //GETTERS

    public int getId() {

        return this.id;
    }

    public String getDescription() {

        return this.description;
    }

    public String getStatus(){

        return this.status;
    }

    public static int getTasksCounter() {
        return  tasksCounter;
    }

    public static DateTimeFormatter getDateFormat (){
        return dateFormat;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return this.updateAt;
    }

    //SETTERS

    public void setDescription(String description){

        this.description = description;
    }

    public  void setStatus(String status){

        this.status = status;
    }

    public static void setTasksCounter(int tasksCounter) {
        Task.tasksCounter = tasksCounter;
    }

    public void setUpdateAt() {
        this.updateAt = LocalDateTime.now();
    }

    @Override
    public String toString(){
        return "Id = " + getId() +'\n' +
                "Description = " + getDescription() +'\n' +
                "Status = " + getStatus() + '\n' +
                "Creation date = " + getCreatedAt() + '\n' +
                "Last update = " + getUpdateAt();
    }






}

