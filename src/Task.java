public class Task {
    private final int id;
    private static int tasksCounter = 0;
    private String description;
    private String status = "Sin comenzar";

    //BUILDERS

    public Task(String description) {
        this.id = ++tasksCounter;
        this.description = description;
    }

    public Task(Integer id, String description, String status){
        this.id = id;
        this.description = description;
        this.status = status;
    }

    //GETTERS

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus(){
        return status;
    }

    public static int getTasksCounter() {
        return tasksCounter;
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

    @Override
    public String toString(){
        return "Id = " + getId() +'\n' +
                "Description = " + getDescription() +'\n' +
                "Status = " + getStatus();
    }
}

