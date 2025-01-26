package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskTrackerService {

    private Scanner scanner;
    private List<Task> tasksList;

    public TaskTrackerService() {
        scanner = new Scanner(System.in);
        tasksList = new ArrayList<>();

    }

    public void addTask(Task task) {
        tasksList.add(task);
    }

    public void updateTask(Task task) {
    }

    public void deleteTask(Task task) {
        tasksList.remove(task);
    }

    public Task getTask(int id) {
        return tasksList.get(id); // We start counting task at 1. We correct the index search.
    }

    public List<Task> getTasksList() {
        return tasksList;
    }
}
