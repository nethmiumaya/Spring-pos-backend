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
@Table(name = "customer")
public class CustomerEntity implements SuperEntity {
    @Id
    private String custId;
    private String custName;
    private String custAddress;
    private double custSalary;
    @OneToMany(mappedBy = "customerEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();
}