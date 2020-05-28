package com.bernard.demo.repositories;

import com.bernard.demo.domains.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectIdentifier(String projectId);

    Iterable<Project> findAllByProjectLeader(String name);
}
