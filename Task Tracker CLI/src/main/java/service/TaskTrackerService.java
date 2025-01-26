package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import model.Status;
import model.Task;
import util.LocalDateTimeAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter

public class TaskTrackerService {


    private List<Task> tasksList;
    private Scanner scanner;
    private final String PATH = "src/main/resources/tasks.json"; // Ruta del archivo JSON

    public TaskTrackerService() {
        scanner = new Scanner(System.in);
        tasksList = loadList();

    }

    public void addTask(Task task) {
        tasksList.add(task);
    }

    public void updateTask(Task task, Status status) {
        task.setStatus(status);
    }

    public void deleteTask(Task task) {
        tasksList.remove(task);
    }

    public Task getTask(int id) {
        return tasksList.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void saveList() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()) // Registra el adaptador para LocalDateTime
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(PATH)) { // Try-with-resources para cerrar el FileWriter automáticamente
            gson.toJson(tasksList, writer); // Escribe la lista de tareas en el archivo JSON
            System.out.println("Tasks saved to " + PATH);
        } catch (IOException e) {
            System.err.println("Error writing to JSON file: " + e.getMessage());
        }
    }

    public List<Task> loadList() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        File file = new File(PATH);

        // Si el archivo no existe, devuelve una lista vacía
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            // Define el tipo de la lista de tareas
            Type taskListType = new TypeToken<List<Task>>() {
            }.getType();

            // Convierte el JSON a una lista de tareas
            List<Task> tasks = gson.fromJson(reader, taskListType);

            // Si el archivo está vacío o no contiene una lista válida, devuelve una lista vacía
            return tasks != null ? tasks : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading from JSON file: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacía si hay un error
        }
    }

}
