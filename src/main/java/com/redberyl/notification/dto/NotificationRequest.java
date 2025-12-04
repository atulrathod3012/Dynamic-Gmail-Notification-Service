package com.redberyl.notification.dto;

import lombok.Data;

@Data
public class NotificationRequest {
    private String tomail;
    private String subject;
    private String body;
}
