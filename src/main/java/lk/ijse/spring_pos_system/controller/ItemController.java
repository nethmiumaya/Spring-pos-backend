package lk.ijse.spring_pos_system.controller;

import lk.ijse.spring_pos_system.customObj.ItemResponse;
import lk.ijse.spring_pos_system.dto.ItemDTO;
import lk.ijse.spring_pos_system.exception.CustomerNotFoundException;
import lk.ijse.spring_pos_system.exception.DataPersistFailedException;
import lk.ijse.spring_pos_system.service.ItemService;
import lk.ijse.spring_pos_system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createItem(@Validated @RequestBody ItemDTO item) {

        try {
            itemService.saveItem(item);
            logger.info("item saved successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Error saving item",e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error occurred while  saving item",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") String itemId) {
        try {
            System.out.println("delete item " + itemId);
            itemService.deleteItem(itemId);
            logger.info("Item Deleted Successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.info("Item Deleted Failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while deleting item",e);
            return new ResponseEntity<>
                    (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/allItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> FindAll() {
        logger.info("Items Retrieve successfully");
        return itemService.getAllItem();
    }

    @GetMapping(value = "/{itemId}",produces =MediaType.APPLICATION_JSON_VALUE )
    public ItemResponse getItem(@PathVariable("itemId") String itemId){
        logger.info("Item Retrieve successfully");
        return itemService.getItem(itemId);
    }

    @PutMapping(value = "/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(
            @PathVariable("itemId") String itemId,
            @RequestBody ItemDTO itemDTO
    ) {
        try {
            itemDTO.setItemId(itemId);
            itemService.updateItem(itemDTO);
            logger.info("Item Updated Successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.info("Item Updated Failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while updating item",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
