package com.redberyl.notification.dto;

import lombok.Data;

@Data
public class NotificationResponse {
    private String status;
    private String message;
    public NotificationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
