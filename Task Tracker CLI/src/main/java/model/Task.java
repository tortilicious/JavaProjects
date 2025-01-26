package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.TaskTrackerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Task {

    private static int idCounter = 1;
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;

        // Obtener la lista de tareas
        TaskTrackerService taskTrackerService = new TaskTrackerService();
        List<Task> tasks = taskTrackerService.getTasksList();

        // Verificar si la lista está vacía
        if (!tasks.isEmpty()) {
            idCounter = tasks.getLast().getId() + 1; // Obtener el último ID y sumar 1
        }
        this.id = idCounter++;

    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCreatedAt = formatter.format(createdAt);
        String formattedUpdatedAt = updatedAt != null ? updatedAt.format(formatter) : "N/A";
        return String.format("id: %d. %s. Status: %s.\n\tCreated: %s.\n\tUpdated: %s\n", id, description, status, formattedCreatedAt, formattedUpdatedAt);
    }
}