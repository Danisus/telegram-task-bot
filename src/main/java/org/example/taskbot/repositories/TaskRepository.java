package org.example.taskbot.repositories;

import org.example.taskbot.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdOrderByPositionAsc(Long userId);

    Optional<Task> findByUserIdAndPosition(Long userId, int position);
}
