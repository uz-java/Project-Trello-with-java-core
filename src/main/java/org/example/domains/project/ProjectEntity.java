package org.example.domains.project;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.domains.Auditable;
import org.example.enums.ProjectStatus;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Table(name = "project")
public class ProjectEntity extends Auditable {
    private String title;
    private String description;
    private String docPath;
    private ProjectStatus status;

    private List<ProjectColumnEntity> projectColumns;

    @Builder(builderMethodName = "childBuilder")
    public ProjectEntity(Long id, Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, int deleted, String title, String description, String docPath, ProjectStatus status, List<ProjectColumnEntity> projectColumns) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.title = title;
        this.description = description;
        this.docPath = docPath;
        this.status = status;
        this.projectColumns = projectColumns;
    }
}
