package org.example.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Auditable {
    @Id
    protected Long id;
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    protected Timestamp updatedAt;
    protected Long createdBy;
    protected Long updatedBy;
    protected int deleted;
}
