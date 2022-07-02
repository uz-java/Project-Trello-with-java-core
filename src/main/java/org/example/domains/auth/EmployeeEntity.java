package org.example.domains.auth;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Table;

@Table(name = "employee")
public class EmployeeEntity {
    private long userId;
    private String fullName;
    @SerializedName("phone_number")
    private String phoneNumber;
    private String email;
}
