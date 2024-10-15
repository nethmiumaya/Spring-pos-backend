package lk.ijse.spring_pos_system.util;

import lk.ijse.spring_pos_system.dto.CustomerDTO;
import lk.ijse.spring_pos_system.dto.ItemDTO;
import lk.ijse.spring_pos_system.dto.OrderDTO;
import lk.ijse.spring_pos_system.dto.OrderDetailDTO;
import lk.ijse.spring_pos_system.entity.CustomerEntity;
import lk.ijse.spring_pos_system.entity.ItemEntity;
import lk.ijse.spring_pos_system.entity.OrderDetailEntity;
import lk.ijse.spring_pos_system.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDTO (CustomerEntity customer){return modelMapper.map(customer,CustomerDTO.class);}
    public CustomerEntity convertToEntity (CustomerDTO dto){return modelMapper.map(dto,CustomerEntity.class);}
    public List<CustomerDTO> convertToDTO(List <CustomerEntity>customers){return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {}.getType()); }

    public ItemDTO convertToItemDTO(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDTO.class);
    }
    public ItemEntity convertToEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public List<ItemDTO> convertToItemDTO(List<ItemEntity> items){
        return modelMapper.map(items, new TypeToken<List<ItemDTO>>() {}.getType());
    }


    public OrderDTO convertToOrderDTO(OrderEntity orderEntity){
        return modelMapper.map(orderEntity, OrderDTO.class);
    }
    public OrderEntity convertToOrderEntity(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, OrderEntity.class);
    }
    public List<OrderDTO> convertToOrderDTO(List<OrderEntity> orders){
        return modelMapper.map(orders, new TypeToken<List<OrderDTO>>() {}.getType());
    }

//    order detail mapping

    public OrderDetailDTO convertToOrderDetailDTO(OrderDetailEntity orderDetailEntity){
        return modelMapper.map(orderDetailEntity, OrderDetailDTO.class);
    }
    public OrderDetailEntity convertToOrderDEtailEntity(OrderDetailDTO orderDTO){
        return modelMapper.map(orderDTO, OrderDetailEntity.class);
    }
    public List<OrderDetailDTO> convertToOrderDetailDTO(List<OrderDetailEntity> orders){
        return modelMapper.map(orders, new TypeToken<List<OrderDetailDTO>>() {}.getType());
    }
}
