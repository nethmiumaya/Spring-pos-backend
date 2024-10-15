package lk.ijse.spring_pos_system.service.serviceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.spring_pos_system.customObj.ItemErrorResponse;
import lk.ijse.spring_pos_system.customObj.ItemResponse;
import lk.ijse.spring_pos_system.dto.ItemDTO;
import lk.ijse.spring_pos_system.entity.CustomerEntity;
import lk.ijse.spring_pos_system.entity.ItemEntity;
import lk.ijse.spring_pos_system.exception.CustomerNotFoundException;
import lk.ijse.spring_pos_system.exception.DataPersistFailedException;
import lk.ijse.spring_pos_system.repository.ItemRepository;
import lk.ijse.spring_pos_system.service.ItemService;
import lk.ijse.spring_pos_system.util.Mapping;
import lk.ijse.spring_pos_system.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemRepository itemRepository;

    private final Mapping mapping;

    @Override
    public void saveItem(ItemDTO item) {
        item.setItemId(Util.createItemId());
        ItemEntity savedItem =
                itemRepository.save(mapping.convertToEntity(item));
        if (savedItem == null && savedItem.getItemId() == null) {
            throw new DataPersistFailedException("cannot data saved");
        }

    }

    @Override
    public void updateItem(ItemDTO item) {
        Optional<ItemEntity>tmpItemEntity =
                itemRepository.findById(item.getItemId());
        if (!tmpItemEntity.isPresent()){
            throw new CustomerNotFoundException("Customer not found");
        }else {
            tmpItemEntity.get().setItemName(item.getItemName());
            tmpItemEntity.get().setItemQty(item.getItemQty());
            tmpItemEntity.get().setItemPrice(item.getItemPrice());
              }
    }

    @Override
    public void deleteItem(String id) {
        Optional<ItemEntity> selectedItemId =
                itemRepository.findById(id);
        System.out.println("selected id  " + selectedItemId.isPresent());
        if (selectedItemId.isPresent()) {
            itemRepository.deleteById(id);
        }
    }

    @Override
    public List<ItemDTO> getAllItem() {
        System.out.println(itemRepository.findAll().size());
        return mapping.convertToItemDTO(itemRepository.findAll());
    }

    @Override
    public ItemResponse getItem(String itemId) {
        if(itemRepository.existsById(itemId)){
            return mapping.convertToItemDTO(itemRepository.getReferenceById(itemId));
        }else {
            return new ItemErrorResponse(0,"Item not found");
        }
    }
}
