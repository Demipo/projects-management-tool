package com.bernard.demo.exceptions;

public class ProjectNotFoundExceptionResponse {

    private String projectNotFound;

    public ProjectNotFoundExceptionResponse(String pageNotFound) {
        this.projectNotFound = pageNotFound;
    }

    public String getprojectNotFound() {
        return projectNotFound;
    }

    public void setprojectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
