package lk.ijse.spring_pos_system.util;

import java.util.UUID;

public class Util {

    public static String createCustomerId() {
        return "C-"+ UUID.randomUUID();
    }


    public static String createItemId() {
        return "I-"+ UUID.randomUUID();
    }

    public static String createOrderId() {
        return "O-"+ UUID.randomUUID();
    }
}