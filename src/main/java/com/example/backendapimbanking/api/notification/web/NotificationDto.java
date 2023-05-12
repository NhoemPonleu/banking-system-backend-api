package com.example.backendapimbanking.api.notification.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
@Builder
public record NotificationDto(@JsonProperty("included_segments")
                              String[] includeSegments,
                              ContentDto contents,
                              @Value("${onesignal.app-id}")
                              @JsonProperty("app_id")
                              String appId) {
}
