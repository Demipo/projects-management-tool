package com.bernard.demo.services;

import com.bernard.ppmtool.domain.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
  List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
  ProjectTask findByProjectSequence(String projectSequence);
 }
