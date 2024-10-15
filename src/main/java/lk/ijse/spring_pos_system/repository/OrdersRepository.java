package lk.ijse.spring_pos_system.repository;

import lk.ijse.spring_pos_system.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity,String> {
}
