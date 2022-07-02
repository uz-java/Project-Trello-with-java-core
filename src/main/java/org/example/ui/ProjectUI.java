package org.example.ui;

import org.example.baseUtils.BaseUtils;
import org.example.baseUtils.Colors;
import org.example.config.AppContextHolder;
import org.example.dto.auth.Session;
import org.example.dto.project.ProjectColumnDTO;
import org.example.dto.project.ProjectDTO;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.dto.task.TaskCreateDTO;
import org.example.services.ProjectService;
import org.example.services.TaskService;
import org.example.services.UserService;

import java.util.Objects;

public class ProjectUI {
    static UserService userService = AppContextHolder.getBean(UserService.class);
    private static TaskService taskService = AppContextHolder.getBean(TaskService.class);


    private static ProjectUI projectUI = new ProjectUI();
    private static ProjectService projectService = AppContextHolder.getBean(ProjectService.class);


    public static void projectWindow(Long projectId) {
        if (Objects.isNull(Session.sessionUser))
            return;


        System.out.println("========================Project Window=======================");


        ResponseEntity<DataDTO<ProjectDTO>> response = projectService.getProjectInfo(projectId, Session.sessionUser.getId());

        print_response(response);

        if (response.getStatus() == 200) {
            BaseUtils.println("Add project column -> 1");
            BaseUtils.println("Add task -> 2");
            BaseUtils.println("Edit project column -> 3");
            BaseUtils.println("Go back -> 4");
            BaseUtils.println("Logout -> 5");
            BaseUtils.println("Quit -> q");
            String option = BaseUtils.readText("?: ");

            switch (option) {
                case "1" -> projectUI.addProjectColumn();
                case "2" -> projectUI.addTask();
                case "3" -> projectUI.editProjectColumn();
                case "4" -> BoardUI.boardWindow();
                case "5" -> Session.sessionUser = null;

                default -> BaseUtils.println("Wrong Choice", Colors.RED);

            }

        } else BoardUI.boardWindow();
        projectWindow(projectId);

    }


    private void editProjectColumn() {

        ProjectColumnDTO projectColumnDTO = ProjectColumnDTO.builder()
                .id(Long.valueOf(BaseUtils.readText("insert project column id ? ")))
                .project_id(Long.valueOf(BaseUtils.readText("insert project id ? ")))
                .name(BaseUtils.readText("insert name of project column ? "))
                .code(BaseUtils.readText("insert code of project column ? "))
                .build();
        ResponseEntity response = projectService.editProjectColumn(projectColumnDTO);
        print_response(response);


    }

    private void addProjectColumn() {
        ProjectColumnDTO projectColumnDTO = ProjectColumnDTO.builder()
                .project_id(Long.valueOf(BaseUtils.readText("project_id ? ")))
                .name(BaseUtils.readText("name of column ? "))
                .code(BaseUtils.readText("insert code ? "))
                .order(Long.valueOf(BaseUtils.readText("insert order ? ")))
                .build();
        System.out.println(Session.sessionUser.getId());
        ResponseEntity<DataDTO<Long>> response = projectService.addProjectColumn(projectColumnDTO, Session.sessionUser.getId());
        print_response(response);

    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

    private void addTask() {
        TaskCreateDTO taskDTO = TaskCreateDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                .priority(BaseUtils.readText("priority ? "))
                .projectColumnId(Long.valueOf(BaseUtils.readText("projectColumnId ? ")))
                .createdBy(Session.sessionUser.getId()).build();
        String option;
        System.out.print("Choose level(default-MEDIUM): ");
        option = BaseUtils.readText("\n1.EASY\n2.MEDIUM\n3.HARD\n?: ");

        switch (option) {
            case "1" -> taskDTO.setLevel("EASY");
            case "2" -> taskDTO.setLevel("HARD");
            default -> taskDTO.setLevel("MEDIUM");
        }

        ResponseEntity<DataDTO<Long>> response = taskService.addTask(taskDTO);
        print_response(response);

    }

}
