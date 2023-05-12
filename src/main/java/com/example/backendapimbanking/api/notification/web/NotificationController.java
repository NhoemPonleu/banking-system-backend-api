package com.example.backendapimbanking.api.notification.web;

import com.example.backendapimbanking.api.notification.NotificationService;
import com.example.backendapimbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    public BaseRest<?>pushNotification(@RequestBody CreateNotificationDto notificationDto){
        notificationService.pushNotification(notificationDto);
        System.out.println(notificationDto.contents());
        return BaseRest.builder()
                .status(true)
                .messages("Notification has been sen ")
                .timeStamp(LocalDateTime.now())
                .code(HttpStatus.OK.value())
                .data(notificationDto.contents())
                .build();
    }
}
