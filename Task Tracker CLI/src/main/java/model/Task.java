package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Task {

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
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedCreatedAt = formatter.format(createdAt);
        String formattedUpdatedAt = updatedAt != null ? updatedAt.format(formatter) : "N/A";
        return String.format("id: %d. %s. Status: %s.\n\tCreated: %s.\n\tUpdated: %s\n", id, description, status, formattedCreatedAt, formattedUpdatedAt);
    }
}