package lk.ijse.spring_pos_system.repository;

import lk.ijse.spring_pos_system.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, String> {
}
