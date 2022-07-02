package org.example.services.auth;

import org.example.baseUtils.BaseUtils;
import org.example.dao.AuthUserDAO;
import org.example.dto.auth.*;
import org.example.dto.response.AppErrorDTO;
import org.example.dto.response.DataDTO;
import org.example.dto.response.ResponseEntity;
import org.example.exceptions.DaoException;
import org.example.config.AppContextHolder;

import java.util.Objects;

public class AuthService {
    private static AuthService authService;

    AuthUserDAO authUserDAO = AppContextHolder.getBean(AuthUserDAO.class);


    public ResponseEntity<DataDTO<UserDTO>> login(AuthLoginDTO authLoginDTO) {
        String login = null;
        try {
            login = authUserDAO.login(authLoginDTO);
            UserDTO userDTO = BaseUtils.gson.fromJson(login, UserDTO.class);
            Session.setSessionUser(userDTO);
            return new ResponseEntity<>(new DataDTO<>(userDTO), 200);
        } catch (DaoException e) {
            return new ResponseEntity<>(
                    new DataDTO<>(
                            AppErrorDTO.builder()
                                    .friendlyMessage(e.getMessage())
                                    .build()),
                    500);
        }

    }


    public static AuthService getInstance() {
        if (Objects.isNull(authService))
            authService = new AuthService();
        return authService;
    }

    public ResponseEntity<DataDTO<Long>>register(AuthCreateDTO authCreateDTO) {
        Long register = null;
        try {
            String authCreateJson = BaseUtils.gson.toJson(authCreateDTO);
            register = authUserDAO.register(authCreateJson);
            UserDTO build = UserDTO.builder()
                    .id(register)
                    .username(authCreateDTO.getUsername())
                    .employee(EmployeeDTO.builder()
                            .fullName(authCreateDTO.getEmployeeCreateDTO().getFullName())
                            .phoneNumber(authCreateDTO.getEmployeeCreateDTO().getPhoneNumber())
                            .email(authCreateDTO.getEmployeeCreateDTO().getEmail())
                            .build())
                    .build();
            Session.setSessionUser(build);
            return new ResponseEntity<>(new DataDTO<>(register), 200);

        } catch (DaoException e) {
            return new ResponseEntity<>(
                    new DataDTO<>(AppErrorDTO.builder().friendlyMessage(e.getMessage()).build()), 500);
        }


    }
}
