package org.example.ui;

import org.example.baseUtils.BaseUtils;
import org.example.baseUtils.Colors;
import org.example.config.AppContextHolder;
import org.example.config.HibernateConfig;
import org.example.dto.auth.Session;
import org.example.dto.project.ProjectCreateDTO;
import org.example.dto.project.ProjectInfoDTO;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.services.ProjectService;
import org.example.services.UserService;

import java.util.List;
import java.util.Objects;

public class BoardUI {

    static UserService userService = AppContextHolder.getBean(UserService.class);
    ProjectService projectService = AppContextHolder.getBean(ProjectService.class);
    static BoardUI boardUI = new BoardUI();


    public static void boardWindow() {
        if (Objects.isNull(Session.sessionUser))
            return;
        System.out.println("========================Board Window=======================");

        ResponseEntity<DataDTO<List<ProjectInfoDTO>>> response = userService.getProjectList(Session.sessionUser.getId());
        print_response(response);

        BaseUtils.println("Add project -> 1");
        BaseUtils.println("project window -> 2");
        BaseUtils.println("Show my tasks -> 3");
        BaseUtils.println("logout -> 4");
        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("?:");
        switch (choice) {

            case "1" -> boardUI.addProject();
            case "2" -> {
                Long projectId = Long.valueOf(BaseUtils.readText("project id ? "));
                ProjectUI.projectWindow(projectId);
            }
            case "3" -> TaskUI.showMyTasks();
            case "4" -> Session.sessionUser = null;
            case "q" -> {
                BaseUtils.println("Bye");
                HibernateConfig.shutdown();
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }

        boardWindow();
    }


    private void addProject() {
        ProjectCreateDTO projectDTO = ProjectCreateDTO.builder()
                .title(BaseUtils.readText("title ? "))
                .description(BaseUtils.readText("description ? "))
                .docPath(BaseUtils.readText("doc_path ? "))
                .createdBy(Session.sessionUser.getId())
                .build();

        ResponseEntity<DataDTO<Long>> response = projectService.addProject(projectDTO);
        print_response(response);
    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);
    }

}