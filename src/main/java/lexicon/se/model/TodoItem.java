package lexicon.se.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lexicon.se.sequencers.TodoItemIdSequencer;

import java.io.File;
import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;
    private final LocalDate now;

    @JsonCreator
    public TodoItem(@JsonProperty("title")String title, @JsonProperty("taskDescription")String taskDescription, @JsonProperty("deadLine")LocalDate deadLine, @JsonProperty("done")boolean done, @JsonProperty("creator")Person creator, @JsonProperty("now")LocalDate now) {
        File fileTodoItem = new File("object_"+title+".json");
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadLine = deadLine;
        this.done = done;
        this.creator = creator;
        this.now = now;
        this.id = TodoItemIdSequencer.nextId();
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
        id++;
    }
    public String getTaskDescription(){
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription){
        this.taskDescription = taskDescription;
    }
    public LocalDate getDeadLine(){
        return deadLine;
    }
    public void setDeadLine(LocalDate deadLine){
        this.deadLine = deadLine;
    }
    public void setDone(boolean done){
        this.done = done;
    }
    public boolean isDone(){
        return done;
    }
    public Person getCreator(){
        return creator;
    }
    public void setCreator(Person creator){
        this.creator = creator;
    }
    public boolean isOverdue() {
        return !deadLine.isBefore(now);
    }
    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", now=" + now +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(taskDescription, todoItem.taskDescription) && Objects.equals(deadLine, todoItem.deadLine) && Objects.equals(now, todoItem.now);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, taskDescription, deadLine, done, now);
    }
}
