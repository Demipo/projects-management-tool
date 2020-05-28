package com.bernard.demo.services;

import com.bernard.demo.domains.Backlog;
import com.bernard.demo.domains.Project;
import com.bernard.demo.domains.User;
import com.bernard.demo.exceptions.ProjectIdExceptionHandler;
import com.bernard.demo.exceptions.ProjectNotFoundExceptionHandler;
import com.bernard.demo.repositories.BacklogRepository;
import com.bernard.demo.repositories.ProjectRepository;
import com.bernard.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username){
        if(project.getId() != null){

            Project checkProjectExistence = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if(checkProjectExistence == null){
                throw new ProjectIdExceptionHandler("Project identifier '" + project.getProjectIdentifier() + "' doesn't exist.");
            }
            if(!checkProjectExistence.getProjectLeader().equals(username)){
                throw new ProjectIdExceptionHandler("Access denied");
            }
        }

        try{
            String projectIdentifierInUpperCase = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(projectIdentifierInUpperCase);

            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(username);


            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectIdentifierInUpperCase);
            }

            if(project.getId() != null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifierInUpperCase));
            }

            return projectRepository.save(project);
        }
        catch (Exception e){
            throw new ProjectIdExceptionHandler("Project identifier " + project.getProjectIdentifier() + " already exists");
        }
    }

    public Project getProjectByIdentifier(String projectId, String username){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdExceptionHandler("Project identifier '" + projectId + "' does not exist");
        }
        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundExceptionHandler("Access denied");
        }
        return project;
    }

    public Iterable<Project> getAllProjects(String name){
        return projectRepository.findAllByProjectLeader(name);
    }

    public void deleteProjectById(String projectId, String username){
        projectRepository.delete(getProjectByIdentifier(projectId, username));
    }

    public void updateProjectById(Project requestProject, String projectId){
        Project persistentProject = projectRepository.findByProjectIdentifier(projectId);
        if(persistentProject == null){
            throw new ProjectIdExceptionHandler("Project identifier " + projectId + " does not exist");
        }
        persistentProject.setDescription(requestProject.getDescription());
        persistentProject.setProjectName(requestProject.getProjectName());
        projectRepository.save(persistentProject);
    }

}
