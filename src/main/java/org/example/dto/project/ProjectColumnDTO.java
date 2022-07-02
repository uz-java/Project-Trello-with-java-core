package org.example.dto.project;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.example.dto.task.TaskDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectColumnDTO {
    private Long id;
    private Long project_id;
    private String name;
    private String code;
    private Long order;
    @SerializedName("tasks")
    private List<TaskDTO> tasks;
}
