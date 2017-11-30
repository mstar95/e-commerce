package pl.ecommerce.backend.time.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TimeManager {

    public LocalDateTime getCurrentDate(){
        return LocalDateTime.now();
    }

    public Timestamp getCurrentTimestamp(){
        return Timestamp.valueOf(getCurrentDate());
    }

    public static Date localDateTimeToDate (LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
