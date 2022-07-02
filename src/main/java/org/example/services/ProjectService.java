package org.example.services;


import org.example.baseUtils.BaseUtils;
import org.example.dao.ProjectDAO;
import org.example.dao.TaskDAO;
import org.example.dto.auth.Session;
import org.example.dto.project.ProjectColumnDTO;
import org.example.dto.project.ProjectCreateDTO;
import org.example.dto.project.ProjectDTO;
import org.example.dto.response.AppErrorDTO;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.exceptions.DaoException;
import org.example.config.AppContextHolder;

import java.util.Objects;

public class ProjectService {
    private static ProjectService projectService;
    TaskDAO taskDAO = AppContextHolder.getBean(TaskDAO.class);

    ProjectDAO projectDAO = AppContextHolder.getBean(ProjectDAO.class);

    public ResponseEntity<DataDTO<Long>> addProject(ProjectCreateDTO projectCreateDTO) {
        Long addProject = null;
        try {
            addProject = projectDAO.addProject(projectCreateDTO);
            return new ResponseEntity<>(new DataDTO<>(addProject), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 400);
        }

    }

    public static ProjectService getInstance() {
        if (Objects.isNull(projectService))
            projectService = new ProjectService();
        return projectService;
    }


    public ResponseEntity<DataDTO<Long>> addProjectColumn(ProjectColumnDTO projectColumnDTO, Long userId) {
        Long addProjectColumn = null;

        try {
            addProjectColumn = projectDAO.addProjectColumn(projectColumnDTO, userId);
            return new ResponseEntity<>(new DataDTO<>(addProjectColumn), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 500);
        }

    }





    public ResponseEntity<DataDTO<ProjectDTO>> getProjectInfo(Long projectId, Long userId) {
        try {
            String projectInfoJson = projectDAO.getProjectInfo(projectId, userId);
            ProjectDTO projectDTO = BaseUtils.gson.fromJson(projectInfoJson, ProjectDTO.class);
            Session.setSessionProject(projectDTO);
            return new ResponseEntity<>(new DataDTO<>(projectDTO),200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getLocalizedMessage())
                    .build()), 500);
        }
    }

    public ResponseEntity editProjectColumn(ProjectColumnDTO projectColumnDTO) {

        Long editProjectColumn = null;

        try {
            editProjectColumn = Long.valueOf(projectDAO.editProjectColumn(projectColumnDTO));
            return new ResponseEntity<>(new DataDTO<>(editProjectColumn), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 400);
        }

    }
}