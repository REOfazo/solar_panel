package com.example.solar_panel.entity;

import com.example.solar_panel.status.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "buyurtma_berish")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class BuyurtmaBerish {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "panel_watt")
    private String productWatt;

    @Column(name = "product_price")
    private String productPrice;

    @ManyToOne
    @JoinColumn(name = "user_product_id")
    private UserEntity userEntity;

    @Column(name = "product_status")
    @ColumnDefault(value = "'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @PrePersist
    public void prePersist() {
        if (this.productStatus == null) {
            this.productStatus = ProductStatus.ACTIVE;
        }
    }

    @Column(name = "buyurtma_vaqti")
    @CreationTimestamp
    private LocalDateTime createTime;
}
