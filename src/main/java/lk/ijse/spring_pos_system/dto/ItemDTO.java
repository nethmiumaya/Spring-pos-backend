package lk.ijse.spring_pos_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lk.ijse.spring_pos_system.customObj.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements SuperDTO, ItemResponse {

    private String itemId;

    @Size(min = 2, max = 50, message = "Item Name name must be between 2 and 50 characters")
    @NotBlank(message = "Name is required")
    private String itemName;

    @Min(value = 1, message = "Item quantity must be at least 1")
    private int itemQty;

    @Min(value = 0, message = "Item price must be at least 0")
    private double itemPrice;
}
