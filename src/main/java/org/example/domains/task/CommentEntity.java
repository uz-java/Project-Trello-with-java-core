package org.example.domains.task;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.domains.Auditable;

import java.sql.Timestamp;

@Table(name = "comment")
@Getter
@Setter
public class CommentEntity extends Auditable {
    private String message;
    private TaskEntity taskId;

    @Builder(builderMethodName = "childBuilder")
    public CommentEntity(Long id, Timestamp createdAt, Timestamp updatedAt, Long createdBy, Long updatedBy, int deleted, String message, TaskEntity taskId) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.message = message;
        this.taskId = taskId;
    }
}
