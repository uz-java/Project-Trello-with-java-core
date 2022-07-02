package org.example.dto.task;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TaskMemberCreateDTO {
    private String email;
    @SerializedName("user_id")
    private Long userId;
    @SerializedName("task_id")
    private Long taskId;
}


