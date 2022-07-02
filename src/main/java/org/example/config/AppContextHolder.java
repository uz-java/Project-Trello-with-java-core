package org.example.config;

import org.example.dao.AuthUserDAO;
import org.example.dao.ProjectDAO;
import org.example.dao.TaskDAO;
import org.example.services.ProjectService;
import org.example.services.TaskService;
import org.example.services.UserService;
import org.example.services.auth.AuthService;

public class AppContextHolder {
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return switch (clazz.getSimpleName()) {
            case "AuthUserDAO" -> (T) AuthUserDAO.getInstance();
            case "AuthService" -> (T) AuthService.getInstance();
            case "UserService" -> (T) UserService.getInstance();
            case "ProjectService" -> (T) ProjectService.getInstance();
            case "ProjectDAO" -> (T) ProjectDAO.getInstance();
            case "TaskDAO" -> (T) TaskDAO.getInstance();
            case "TaskService" -> (T) TaskService.getInstance();
            default -> throw new RuntimeException("Bean not found");
        };
    }

    public static Object getBean(String beanName) {
        return switch (beanName) {
            case "AuthUserDAO" -> AuthUserDAO.getInstance();
            case "AuthService" -> AuthService.getInstance();
            case "UserService" -> UserService.getInstance();
            case "ProjectDAO" -> ProjectDAO.getInstance();
            case "TaskDAO" -> TaskDAO.getInstance();
            default -> throw new RuntimeException("Bean not found");
        };
    }
}
