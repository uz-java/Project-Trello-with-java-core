package org.example.dto.task;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class CommentCreateDTO {

    private String message;
    @SerializedName("task_id")
    private Long taskId;
    @SerializedName("created_by")
    private Long createdBy;
}
