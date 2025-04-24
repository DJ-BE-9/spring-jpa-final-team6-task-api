package com.nhnacademy.repository;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProject(Project project);
    boolean existsByTaskTitle(String title);
}
