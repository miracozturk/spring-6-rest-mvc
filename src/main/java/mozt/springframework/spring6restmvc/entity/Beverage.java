package mozt.springframework.spring6restmvc.entity;

import jakarta.persistence.*;
import lombok.*;
import mozt.springframework.spring6restmvc.model.BeverageStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Beverage {

    @GeneratedValue(generator="UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    @Id
    private UUID id;

    private String beverageName;

    @Version
    private Integer version;
    private BeverageStyle beverageStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
