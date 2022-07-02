package org.example.dto.project;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Id;
import lombok.*;
import org.example.enums.ProjectStatus;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ProjectInfoDTO {
    @Id
    private Long id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("doc_path")
    private String docPath;

    private ProjectStatus status;
    @SerializedName("created_by")
    private Long createdBy;
}
