package lk.ijse.spring_pos_system.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderErrorResponse implements OrderResponse, Serializable {
    private int errorCode;
    private String errorMessage;
}
