package org.example.ui;


import org.example.baseUtils.BaseUtils;
import org.example.baseUtils.Colors;
import org.example.config.AppContextHolder;
import org.example.config.HibernateConfig;
import org.example.dto.auth.*;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.services.auth.AuthService;

import java.util.Objects;

public final class AuthUI {

    AuthService authService = AppContextHolder.getBean(AuthService.class);

    public static void main(String[] args) {

        AuthUI authUI = new AuthUI();

        if (Objects.isNull(Session.sessionUser)) {
            BaseUtils.println("Login -> 1");
            BaseUtils.println("Register -> 2");
            BaseUtils.println("Quit -> q");

            String choice = BaseUtils.readText("?:");
            switch (choice) {
                case "1" -> authUI.login();
                case "2" -> authUI.register();
                case "q" -> {
                    BaseUtils.println("Bye");
                    HibernateConfig.shutdown();
                    System.exit(0);
                }
                default -> BaseUtils.println("Wrong Choice", Colors.RED);
            }
        } else BoardUI.boardWindow();


        main(args);

    }

    private void register() {

        AuthCreateDTO authCreateDTO = AuthCreateDTO.builder()
                .username(BaseUtils.readText("username ?"))
                .password(BaseUtils.readText("password ? "))
                .employeeCreateDTO(EmployeeCreateDTO.builder()
                        .fullName(BaseUtils.readText("fullName ? "))
                        .phoneNumber(BaseUtils.readText("phoneNumber ? "))
                        .email(BaseUtils.readText("email ? "))
                        .build())
                .build();


        String option;
        System.out.println("Choose language(default-RU): ");
        option = BaseUtils.readText("\n1.EN\n2.RU\n3.UZ\n?: ");


        switch (option) {
            case "1" -> authCreateDTO.setLanguage("EN");
            case "2" -> authCreateDTO.setLanguage("UZ");
            default -> authCreateDTO.setLanguage("RU");
        }

        ResponseEntity<DataDTO<Long>> response = authService.register(authCreateDTO);
        print_response(response);
    }

    private void login() {
        AuthLoginDTO authLoginDTO = AuthLoginDTO.builder()
                .username(BaseUtils.readText("username ? "))
                .password(BaseUtils.readText("password ? "))
                .build();

        ResponseEntity<DataDTO<UserDTO>> response = authService.login(authLoginDTO);
        print_response(response);

    }

    public static void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response), color);

    }
}
