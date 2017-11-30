package pl.ecommerce.backend.base

import java.sql.Timestamp
import java.time.LocalDateTime;

class TimeTestData {
    static def BASIC_DATA = LocalDateTime.of(2017,11,04,15,50,30)
    static def BASIC_DATA_TIMESTAMP = Timestamp.valueOf(BASIC_DATA)
    static def BASIC_DATA_LATER = LocalDateTime.of(2017,12,04,15,50,30)
    static def BASIC_DATA_LATER_TIMESTAMP = Timestamp.valueOf(BASIC_DATA_LATER)
}
