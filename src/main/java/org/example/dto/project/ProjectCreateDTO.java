package org.example.dto.project;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProjectCreateDTO {
    private String title;
    private String description;

    @SerializedName("doc_path")
    private String docPath;
    private String status;

    @SerializedName("created_at")
    private Timestamp createdAt;

    @SerializedName("created_by")
    private Long createdBy;
}
