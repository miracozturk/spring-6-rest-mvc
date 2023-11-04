package mozt.springframework.spring6restmvc.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Customer {
    private String customerName;
    private UUID id;
    private int version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
