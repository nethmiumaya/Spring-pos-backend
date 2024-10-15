// src/main/java/lk/ijse/spring_pos_system/entity/ItemEntity.java
package lk.ijse.spring_pos_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemId;
    private String itemName;
    private int itemQty;
    private double itemPrice;
}