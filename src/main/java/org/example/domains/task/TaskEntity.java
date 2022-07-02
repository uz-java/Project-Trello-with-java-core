package org.example.domains.task;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.domains.Auditable;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class TaskEntity extends Auditable {
    private String title;
    private String description;
    @SerializedName("level")
    private String taskLevel;
    private String priority;

    @SerializedName("project_column_id")
    @Transient
    private Long projectColumnId;
    private Long order;

    @Builder(builderMethodName = "childBuilder")
    public TaskEntity(Long id, Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, int deleted, String title, String description, String taskLevel, String priority, Long projectColumnId, Long order) {
        super(id,createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.title = title;
        this.description = description;
        this.taskLevel = taskLevel;
        this.priority = priority;
        this.projectColumnId = projectColumnId;
        this.order = order;
    }

    public TaskEntity() {

    }
}
