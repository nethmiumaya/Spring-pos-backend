package lk.ijse.spring_pos_system.repository;

import lk.ijse.spring_pos_system.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,String> {
//ItemEntity getItemEntityByItemCode(String itemCode);
}
