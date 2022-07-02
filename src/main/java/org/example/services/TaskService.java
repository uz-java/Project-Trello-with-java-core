package org.example.services;

import org.example.baseUtils.BaseUtils;
import org.example.dao.TaskDAO;
import org.example.dto.response.AppErrorDTO;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.dto.task.CommentCreateDTO;
import org.example.dto.task.TaskCreateDTO;
import org.example.dto.task.TaskDTO;
import org.example.dto.task.TaskMemberCreateDTO;
import org.example.exceptions.DaoException;
import org.example.config.AppContextHolder;

import java.util.Objects;

public class TaskService {
    private static TaskService taskService;
    TaskDAO taskDAO = AppContextHolder.getBean(TaskDAO.class);

    public static TaskService getInstance() {
        if (Objects.isNull(taskService))
            taskService = new TaskService();
        return taskService;
    }

    public ResponseEntity<DataDTO<Long>> addTask(TaskCreateDTO taskCreateDTO) {
        Long addTask = null;
        try {
            String response = BaseUtils.gson.toJson(taskCreateDTO);
            addTask = taskDAO.addTask(response);
            return new ResponseEntity<>(new DataDTO<>(addTask), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 500);
        }
    }

    public ResponseEntity<DataDTO<String>> addTaskMember(TaskMemberCreateDTO taskMemberCreateDTO) {
        try {
            String taskMemberCreateJson = BaseUtils.gson.toJson(taskMemberCreateDTO);
            Boolean addTaskMember = taskDAO.addTaskMember(taskMemberCreateJson);
            if (addTaskMember)
                return new ResponseEntity<>(new DataDTO<>("member added successfully"), 200);
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("oops something went wrong")
                    .build()), 400);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage()).build()), 400);
        }
    }

    public ResponseEntity<DataDTO<String>> addCommentToTask(CommentCreateDTO commentCreateDTO) {
        try {
            String commentCreateJson = BaseUtils.gson.toJson(commentCreateDTO);
            Long result = taskDAO.addComment(commentCreateJson);
            if (Objects.nonNull(result))
                return new ResponseEntity<>(new DataDTO<>("comment added successfully"), 200);
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage("oops something went wrong")
                    .build()), 400);

        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getLocalizedMessage())
                    .build()), 500);
        }
    }

    public ResponseEntity<DataDTO<TaskDTO>> getTaskById(Long taskId, Long userId) {
        try {
            String taskJson = taskDAO.getTaskInfo(taskId, userId);
            TaskDTO taskDTO = BaseUtils.gson.fromJson(taskJson, TaskDTO.class);
            return new ResponseEntity<>(new DataDTO<>(taskDTO),200);
        } catch (DaoException e) {
            return new ResponseEntity<>(new DataDTO<>(AppErrorDTO.builder()
                    .friendlyMessage(e.getMessage())
                    .build()), 500);
        }

    }

    public void updateTask(TaskDTO task, Long userId) {

        try {
            taskDAO.updateTask(task,userId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }
}
