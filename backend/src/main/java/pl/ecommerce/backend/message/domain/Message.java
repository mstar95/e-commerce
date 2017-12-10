package pl.ecommerce.backend.message.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long addresseeId;
    private String partnerName;
    private String productName;
    private Timestamp created;
    private BigDecimal amount;
    private boolean seen;
    private MessageType messageType;

}
