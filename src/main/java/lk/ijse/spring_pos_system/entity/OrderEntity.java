package lk.ijse.spring_pos_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class OrderEntity implements SuperEntity {
    @Id
    private String orderId;
    private String orderDate;
    @ManyToOne
    @JoinColumn(name = "custId", nullable = false)
    private CustomerEntity customerEntity;
    private double total;
    private String discount;
    private double subTotal;
    private double cash;
    private double balance;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetailEntity> orderDetails= new ArrayList<>();
}