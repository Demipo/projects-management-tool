package com.bernard.demo.services;

import com.bernard.ppmtool.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectIdentifier(String projectId);

    Iterable<Project> findAllByProjectLeader(String name);
}
