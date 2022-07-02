package org.example.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.project.ProjectDTO;

public class Session {
    public static SessionUser sessionUser;
    public static SessionProject sessionProject;

    public static void setSessionUser(UserDTO session) {
        sessionUser = new SessionUser(session);
    }

    public static void setSessionProject(ProjectDTO projectDTO) {
        sessionProject =new SessionProject(projectDTO);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionUser {
        private Long id;
        private String username;

        public SessionUser(UserDTO session) {
            this.id = session.getId();
            this.username = session.getUsername();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionProject {
        private Long projectId;
        private String projectTitle;

        public SessionProject(ProjectDTO session) {
            this.projectId = session.getId();
            this.projectTitle = session.getTitle();
        }
    }
}
