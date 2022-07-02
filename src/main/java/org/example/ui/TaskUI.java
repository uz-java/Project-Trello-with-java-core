package org.example.ui;

import org.example.baseUtils.BaseUtils;
import org.example.baseUtils.Colors;
import org.example.config.AppContextHolder;
import org.example.dto.auth.Session;
import org.example.dto.project.ProjectDTO;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.dto.task.CommentCreateDTO;
import org.example.dto.task.TaskDTO;
import org.example.dto.task.TaskInfoDTO;
import org.example.dto.task.TaskMemberCreateDTO;
import org.example.services.ProjectService;
import org.example.services.TaskService;
import org.example.services.UserService;

import java.util.List;

public class TaskUI {
    private static final UserService userService = AppContextHolder.getBean(UserService.class);

    private static final TaskService taskService = AppContextHolder.getBean(TaskService.class);
    private static final ProjectService projectService = AppContextHolder.getBean(ProjectService.class);
    private static final TaskUI taskUI = new TaskUI();

    public static void showMyTasks() {
        System.out.println("===========================Task window================================");

        ResponseEntity<DataDTO<List<TaskInfoDTO>>> response = userService.getTaskList(Session.sessionUser.getId());
        print_response(response);

        if (response.getStatus() == 200) {
            BaseUtils.println("\n\n" + "Edit task -> 1");
            BaseUtils.println("task details -> 2");
            BaseUtils.println("go back ->3");
            String choice = BaseUtils.readText("?:");
            switch (choice) {

                case "1" -> taskUI.editTask();
                case "2" -> taskUI.showTaskDetails();
                case "3" -> BoardUI.boardWindow();
                default -> BaseUtils.println("Wrong Choice", Colors.RED);

            }
        } else BoardUI.boardWindow();
        showMyTasks();
    }

    private void addCommentToTask() {
        CommentCreateDTO commentCreateDTO = CommentCreateDTO.builder()
                .createdBy(Session.sessionUser.getId())
                .taskId(Long.valueOf(BaseUtils.readText("Task id? ")))
                .message(BaseUtils.readText("Message? "))
                .build();
        taskService.addCommentToTask(commentCreateDTO);
    }


    private void showTaskDetails() {
        Long taskId = Long.valueOf(BaseUtils.readText("Task id? "));
        ResponseEntity<DataDTO<TaskDTO>> response = taskService.getTaskById(taskId, Session.sessionUser.getId());
        print_response(response);
    }

    private void editTask() {
        BaseUtils.println("Add member to task -> 1 ");
        BaseUtils.println("Add comment to task -> 2 ");
        BaseUtils.println("Change task column -> 3 ");

        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> addMemberToTask();
            case "2" -> addCommentToTask();
            case "3" -> changeTaskColumn();
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
    }

    private void changeTaskColumn() {
        Long taskId = Long.valueOf(BaseUtils.readText("taskId ? "));
        ResponseEntity<DataDTO<TaskDTO>> response = taskService.getTaskById(taskId, Session.sessionUser.getId());
        if (response.getStatus() != 200) {
            print_response(response);
            return;
        }

        TaskDTO task = response.getData().getBody();
        Long project_id = task.getProject_id();
        ResponseEntity<DataDTO<ProjectDTO>> projectResponse = projectService.getProjectInfo(project_id, Session.sessionUser.getId());
        print_response(projectResponse);


        Long projectColumnId = Long.valueOf(BaseUtils.readText("project Column Id ? "));

        task.setProjectColumnId(projectColumnId);

        taskService.updateTask(task, Session.sessionUser.getId());
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

    private void addMemberToTask() {
        TaskMemberCreateDTO taskMemberCreateDTO = TaskMemberCreateDTO.builder()
                .taskId(Long.valueOf(BaseUtils.readText("taskId ? ")))
                .email(BaseUtils.readText("email ? "))
                .userId(Session.sessionUser.getId()).build();

        ResponseEntity<DataDTO<String>> response = taskService.addTaskMember(taskMemberCreateDTO);
        print_response(response);
    }
}
