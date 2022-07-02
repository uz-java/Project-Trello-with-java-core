package org.example.dto.task;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Comment")
public class CommentDTO {

    @Id
    private Long id;
    private String message;
    @SerializedName("task_id")
    private Long taskId;
    @SerializedName("created_by")
    private Long createdBy;
}
