// src/main/java/lk/ijse/spring_pos_system/entity/OrderDetailEntity.java
package lk.ijse.spring_pos_system.entity;

import jakarta.persistence.*;
import lk.ijse.spring_pos_system.embedded.OrderDetailPrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_detail")
public class OrderDetailEntity implements SuperEntity {
    @EmbeddedId
    private OrderDetailPrimaryKey id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;
    private int qty;
}