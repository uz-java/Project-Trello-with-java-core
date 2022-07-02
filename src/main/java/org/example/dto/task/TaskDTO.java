package org.example.dto.task;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private String level;
    private String priority;

    @SerializedName("project_column_id")
    private Long projectColumnId;
    @SerializedName("project_id")
    private Long project_id;
    private Long order;
    @SerializedName("created_by")
    private Long createdBy;

    @SerializedName("comments")
    private List<CommentCreateDTO> commentDTOS = new ArrayList<>();

}
