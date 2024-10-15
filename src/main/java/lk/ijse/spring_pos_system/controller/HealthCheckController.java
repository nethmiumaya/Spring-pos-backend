package lk.ijse.spring_pos_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthtest")
public class HealthCheckController {

    @GetMapping
    public String healthTest(){
        return "Spring POS System is working";
    }
}
