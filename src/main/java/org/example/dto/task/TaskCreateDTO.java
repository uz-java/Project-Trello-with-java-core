package org.example.dto.task;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TaskCreateDTO {
    private String title;
    private String description;
    private String level;
    private String priority;
    @SerializedName("project_column_id")
    private Long projectColumnId;
    @SerializedName("created_by")
    private Long createdBy;
}
