package lk.ijse.spring_pos_system.service.serviceImpl;

import lk.ijse.spring_pos_system.dto.ItemDTO;
import lk.ijse.spring_pos_system.dto.OrderDTO;
import lk.ijse.spring_pos_system.dto.OrderDetailDTO;
import lk.ijse.spring_pos_system.entity.CustomerEntity;
import lk.ijse.spring_pos_system.entity.ItemEntity;
import lk.ijse.spring_pos_system.entity.OrderDetailEntity;
import lk.ijse.spring_pos_system.entity.OrderEntity;
import lk.ijse.spring_pos_system.embedded.OrderDetailPrimaryKey;
import lk.ijse.spring_pos_system.exception.DataPersistFailedException;
import lk.ijse.spring_pos_system.repository.CustomerRepository;
import lk.ijse.spring_pos_system.repository.ItemRepository;
import lk.ijse.spring_pos_system.repository.OrderDetailRepository;
import lk.ijse.spring_pos_system.repository.OrdersRepository;
import lk.ijse.spring_pos_system.service.OrderService;
import lk.ijse.spring_pos_system.util.Mapping;

import lk.ijse.spring_pos_system.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrderService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrder(OrderDTO orderDto) {
        orderDto.setOrderId(Util.createOrderId());
        System.out.println("orderDto = " + orderDto);
        OrderEntity orderEntity = mapping.convertToOrderEntity(orderDto);
        CustomerEntity customerEntity = customerRepository.findById(orderDto.getCustId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        orderEntity.setCustomerEntity(customerEntity);
        System.out.println("orderEntity = " + orderEntity);
        OrderEntity orderSave=orderRepository.save(orderEntity);
        if (orderSave == null && orderSave.getOrderId() == null) {
            throw new DataPersistFailedException();
        }else {
            for (ItemDTO itemDTO:orderDto.getItems()){
                Optional<ItemEntity> item = itemRepository.findById(itemDTO.getItemId());
                if (!item.isPresent()) {
                    throw new RuntimeException("Item not found: " + itemDTO.getItemId());
                }
                OrderDetailDTO orderDetailDTO=new OrderDetailDTO();
                orderDetailDTO.setOrder_id(orderDto.getOrderId());
                orderDetailDTO.setQty(itemDTO.getItemQty());
                orderDetailDTO.setItem_code(itemDTO.getItemId());

                OrderDetailPrimaryKey orderDetailPrimaryKey=new OrderDetailPrimaryKey(orderDto.getOrderId(),itemDTO.getItemId());
                OrderDetailEntity orderDetailEntity = mapping.convertToOrderDEtailEntity(orderDetailDTO);
                orderDetailEntity.setId(orderDetailPrimaryKey);
                orderDetailEntity.setOrder(orderSave);
                orderDetailEntity.setItem(item.get());

                OrderDetailEntity saveOrderDetail=orderDetailRepository.save(orderDetailEntity);
                if (saveOrderDetail == null && saveOrderDetail.getOrder().getOrderId() == null) {
                    throw new DataPersistFailedException();
                }else {
                    ItemEntity itemEntity=mapping.convertToEntity(itemDTO);
                    itemEntity.setItemQty((item.get().getItemQty())-itemDTO.getItemQty());
                    itemRepository.save(itemEntity);
                }
            }
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orderDTOS = mapping.convertToOrderDTO(orderRepository.findAll());
        return orderDTOS;

    }
}