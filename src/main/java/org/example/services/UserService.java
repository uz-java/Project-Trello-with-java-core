package org.example.services;

import com.google.gson.reflect.TypeToken;
import org.example.baseUtils.BaseUtils;
import org.example.dao.ProjectDAO;
import org.example.dao.TaskDAO;
import org.example.dto.project.ProjectInfoDTO;
import org.example.dto.response.AppErrorDTO;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.dto.task.TaskInfoDTO;
import org.example.exceptions.DaoException;
import org.example.config.AppContextHolder;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static UserService userService;
    ProjectDAO projectDAO = AppContextHolder.getBean(ProjectDAO.class);
    TaskDAO taskDAO = AppContextHolder.getBean(TaskDAO.class);


    public static UserService getInstance() {
        if (Objects.isNull(userService))
            userService = new UserService();
        return userService;
    }

    public ResponseEntity<DataDTO<List<ProjectInfoDTO>>> getProjectList(Long id) {
        try {
            String projectList = projectDAO.getProjectList(id);

            Type type = new TypeToken<ArrayList<ProjectInfoDTO>>() {
            }.getType();


            ArrayList<ProjectInfoDTO> result = BaseUtils.gson.fromJson(projectList, type);

            if (result.isEmpty())
                return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                        .friendlyMessage("You do not have any projects")
                        .build()), 404);

            return new ResponseEntity<>(new DataDTO<>(result), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("Oops something went wrong")
                    .build()), 500);
        }

    }

    public ResponseEntity<DataDTO<List<TaskInfoDTO>>> getTaskList(Long id) {
        try {
            String taskList = taskDAO.getTaskList(id);
            Type type = new TypeToken<ArrayList<TaskInfoDTO>>() {
            }.getType();

            ArrayList<TaskInfoDTO> result = BaseUtils.gson.fromJson(taskList, type);
            if (result.isEmpty())
                return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                        .friendlyMessage("You do not have any tasks")
                        .build()), 404);

            return new ResponseEntity<>(new DataDTO<>(result), 200);

        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("Oops something went wrong")
                    .build()), 500);
        }
    }
}
