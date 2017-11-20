package pl.ecommerce.backend.time.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TimeManager {

    public LocalDateTime getCurrentDate(){
        return LocalDateTime.now();
    }

    public Timestamp getCurrentTimestamp(){
        return Timestamp.valueOf(getCurrentDate());
    }

}
