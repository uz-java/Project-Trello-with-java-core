package org.example.domains.auth;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.domains.Auditable;
import org.example.enums.Language;
import org.example.enums.UserRole;
import org.example.enums.UserStatus;

import java.sql.Timestamp;

@Table(name = "users")
@Getter
@Setter
public class UserEntity extends Auditable {
    private String username;
    private String password;
    private UserStatus status;
    private UserRole role;
    private Language language;
    @SerializedName("employee")
    private EmployeeEntity employeeEntity;

    @Builder(builderMethodName = "childBuilder")
    public UserEntity(Long id, Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, int deleted, String username, String password, UserStatus status, UserRole role, Language language, EmployeeEntity employeeEntity) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
        this.language = language;
        this.employeeEntity = employeeEntity;
    }
}
