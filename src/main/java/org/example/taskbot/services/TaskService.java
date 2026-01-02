package org.example.taskbot.services;

import lombok.RequiredArgsConstructor;
import org.example.taskbot.entities.Task;
import org.example.taskbot.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getTasks(Long userId) {
        return taskRepository.findByUserIdOrderByPositionAsc(userId);
    }

    public Task addTask(Long userId, String description) {
        int nextPosition = getTasks(userId).isEmpty() ? 1: getTasks(userId).size() + 1;
        Task task = Task.builder()
                .description(description)
                .userId(userId)
                .position(nextPosition)
                .build();
        return taskRepository.save(task);
    }

    public void completeTask(Long userId, int number){
        Task task = taskRepository.findByUserIdAndPosition(userId, number).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        taskRepository.save(task);
    }

    public void deleteTask(Long userId, int number){
        Task task = taskRepository.findByUserIdAndPosition(userId, number).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }

}
