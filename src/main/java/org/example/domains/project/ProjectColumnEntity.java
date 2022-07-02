package org.example.domains.project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class ProjectColumnEntity {
    @Id
    private Long id;
    private String name;
    private String code;
    private Long project_id;
}
