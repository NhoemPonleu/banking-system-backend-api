package com.example.backendapimbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class BackendApiMbankingApplication {
    @GetMapping("verify/email")
    public String getVerify(){
        return "auth/verify";
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApiMbankingApplication.class, args);
    }

}
