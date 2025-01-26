package cli;

import model.Status;
import model.Task;
import service.TaskTrackerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskTrackerCLI {

    private TaskTrackerService taskTrackerService;
    private Scanner scanner;

    public TaskTrackerCLI() {
        taskTrackerService = new TaskTrackerService();
        scanner = new Scanner(System.in);
    }

    public void run() {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addTask();
                case 2 -> updateTask();
                case 3 -> deleteTask();
                case 4 -> showTaskList();
                case 5 -> exit();
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 5);
    }

    public void addTask() {

        System.out.println("Add a description for the new task: ");
        String description = scanner.nextLine();

        // Comprobamos que el input sea correcto
        if (description == null || description.trim().isEmpty()) {
            System.out.println("Error: Description cannot be empty.");
            System.out.println();
            return;
        }

        taskTrackerService.addTask(new Task(description));
        System.out.println("The task has been added successfully.");
        System.out.println();
    }

    private void deleteTask() {
        System.out.println("Enter the task id you want to delete: ");
        try {
            int taskId = Integer.parseInt(scanner.nextLine());
            Task taskToDelete = taskTrackerService.getTasksList().stream()
                    .filter(task -> task.getId() == taskId)
                    .findFirst()
                    .orElse(null);

            if (taskToDelete == null) {
                System.out.println("Could not find task with id: " + taskId);
                return;
            }

            taskTrackerService.deleteTask(taskToDelete);
            System.out.println("The task has been deleted successfully.");
            System.out.println();

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
            System.out.println();
        }
    }

    private void exit() {
        System.out.println("Exiting...");
        taskTrackerService.saveList();
        System.exit(0);
    }

    public void showMenu() {
        System.out.println("""
                1. Add Task
                2. Update Task
                3. Delete Task
                4. Show Tasks
                5. Exit
                """);
    }

    public void showStatusMenu() {
        System.out.println("""
                1. TO DO
                2. IN_PROGRESS
                3. DONE
                4. ALL
                5. Exit
                """);
    }

    public void updateTask() {
        System.out.println("Enter task id to update: ");

        try {
            int taskId = Integer.parseInt(scanner.nextLine());

            // Buscar la tarea por ID (no por índice)
            Task taskToUpdate = taskTrackerService.getTask(taskId);

            if (taskToUpdate == null) {
                System.out.println("Could not find task with id: " + taskId);
                return;
            }

            System.out.println("Set new status for the task. Current task status: " + taskToUpdate.getStatus());
            showStatusMenu();

            int userChoice = Integer.parseInt(scanner.nextLine());

            // Actualizar el estado de la tarea
            switch (userChoice) {
                case 1:
                    taskToUpdate.setStatus(Status.TODO);
                    break;
                case 2:
                    taskToUpdate.setStatus(Status.IN_PROGRESS);
                    break;
                case 3:
                    taskToUpdate.setStatus(Status.DONE);
                    break;
                case 4:
                    return; // Salir sin hacer cambios
                default:
                    System.out.println("Invalid option. No changes were made.");
                    return;
            }

            // Actualizar la fecha de modificación
            taskToUpdate.setUpdatedAt(LocalDateTime.now());
            System.out.println("Task updated successfully!");
            System.out.println();

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
            System.out.println();
        }
    }

    public void showTaskList() {
        System.out.println("Filter tasks by status:");
        showStatusMenu(); // Mostrar el menú de estados

        try {
            int option = Integer.parseInt(scanner.nextLine());

            // Filtrar y mostrar las tareas según la opción seleccionada
            List<Task> filteredTasks = filterTasksByStatus(option);

            if (filteredTasks.isEmpty()) {
                System.out.println("No tasks found with the selected status.");
            } else {
                filteredTasks.forEach(task -> System.out.println(task.toString()));
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        }
    }

    private List<Task> filterTasksByStatus(int option) {
        List<Task> tasks = taskTrackerService.getTasksList();

        switch (option) {
            case 1:
                return tasks.stream()
                        .filter(task -> task.getStatus() == Status.TODO)
                        .collect(Collectors.toList());
            case 2:
                return tasks.stream()
                        .filter(task -> task.getStatus() == Status.DONE)
                        .collect(Collectors.toList());
            case 3:
                return tasks.stream()
                        .filter(task -> task.getStatus() == Status.IN_PROGRESS)
                        .collect(Collectors.toList());
            case 4:
                return tasks; // Mostrar todas las tareas
            default:
                System.out.println("Invalid option. Showing all tasks.");
                return tasks; // Mostrar todas las tareas por defecto
        }
    }


}
