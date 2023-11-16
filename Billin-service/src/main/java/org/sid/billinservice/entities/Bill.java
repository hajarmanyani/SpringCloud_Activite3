package org.sid.billinservice.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.billinservice.model.Customer;

import java.util.Date;
import java.util.List;
@Data
@Entity @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billDate;
    private Long customerId;
    private Long productId;
    @OneToMany(mappedBy = "bill")
    private List<productItem> productItems;
    @Transient
    private Customer customer;
}
