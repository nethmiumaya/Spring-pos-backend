package lk.ijse.spring_pos_system.service;

import lk.ijse.spring_pos_system.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();

}
