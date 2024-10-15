package lk.ijse.spring_pos_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lk.ijse.spring_pos_system.customObj.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO, CustomerResponse {

    private String custId;

    @NotNull(message = "Customer name cannot be null")
    @Size(min = 2, max = 50, message = "Customer name must be between 2 and 50 characters")
    @NotBlank(message = "Name is required")
    private String custName;

    @NotNull(message = "Customer address cannot be null")
    private String custAddress;

    @NotNull(message = "Customer salary cannot be null")
    private double custSalary;
}
