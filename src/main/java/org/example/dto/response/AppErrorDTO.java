package org.example.dto.response;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AppErrorDTO {
    private Timestamp timestamp;
    private String friendlyMessage;
    private String developerMessage;

    public AppErrorDTO(String friendlyMessage, String developerMessage) {
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
        this.friendlyMessage = friendlyMessage;
        this.developerMessage = developerMessage;
    }

    public AppErrorDTO(String friendlyMessage) {
        this(friendlyMessage, friendlyMessage);
    }
}
