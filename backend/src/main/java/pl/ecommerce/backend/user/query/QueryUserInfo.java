package pl.ecommerce.backend.user.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
class QueryUserInfo {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "userId")
    private QueryUser user;
    private BigDecimal points;
}
