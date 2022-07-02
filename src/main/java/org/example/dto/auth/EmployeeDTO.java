package org.example.dto.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class EmployeeDTO {
    @SerializedName("user_id")
    private Long userId;
    @SerializedName("full_name")
    private String fullName;

    @SerializedName("phone_number")
    private String phoneNumber;

    private String email;
}
