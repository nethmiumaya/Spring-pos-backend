package lk.ijse.spring_pos_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements SuperDTO {

    private String orderId;

    @NotNull(message = "Order date cannot be null")
    private String orderDate;

    @NotNull(message = "Customer ID cannot be null")
    private String custId;

    @NotNull(message = "Items cannot be null")
    @Size(min = 1, message = "Order must contain at least one item")
    private List<ItemDTO> items;

    @Min(value = 0, message = "Total must be at least 0")
    private double total;

    private String discount;

    @Min(value = 0, message = "Subtotal must be at least 0")
    private double subTotal;

    @Min(value = 0, message = "Cash must be at least 0")
    private double cash;

    @Min(value = 0, message = "Balance must be at least 0")
    private double balance;
}
