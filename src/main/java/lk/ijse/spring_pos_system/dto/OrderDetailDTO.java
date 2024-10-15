package lk.ijse.spring_pos_system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class OrderDetailDTO implements SuperDTO {

    private String order_id;

    @NotNull(message = "Item code cannot be null")
    private String item_code;

    @Min(value = 1, message = "Quantity must be at least 1")
    @NotBlank(message = "Quantity is required")
    private int qty;
}
