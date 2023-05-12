package com.example.backendapimbanking.api.notification;

import com.example.backendapimbanking.api.notification.web.CreateNotificationDto;
import com.example.backendapimbanking.api.notification.web.NotificationDto;

public interface NotificationService {
    boolean pushNotification(CreateNotificationDto notificationDto);
}
