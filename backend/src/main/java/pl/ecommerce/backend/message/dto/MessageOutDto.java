package pl.ecommerce.backend.message.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class MessageOutDto {
    String text;
    boolean seen;
    LocalDateTime created;
}
