package lk.ijse.spring_pos_system.service;

import lk.ijse.spring_pos_system.customObj.CustomerResponse;
import lk.ijse.spring_pos_system.customObj.ItemResponse;
import lk.ijse.spring_pos_system.dto.CustomerDTO;
import lk.ijse.spring_pos_system.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(ItemDTO item);
    void deleteItem(String id);
    List<ItemDTO> getAllItem();
    ItemResponse getItem(String itemId);
}
