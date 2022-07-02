package org.example.dto.auth;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthCreateDTO {
    @SerializedName("userName")
    private String username;
    private String password;
    @SerializedName("employee")
    private EmployeeCreateDTO employeeCreateDTO;
    private String language;
}
