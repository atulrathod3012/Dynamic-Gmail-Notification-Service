package com.redberyl.notification.controller;

import com.redberyl.notification.service.NotificationService;
import com.redberyl.notification.dto.NotificationRequest;
import com.redberyl.notification.dto.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/sendNotification")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest req) {
        if (req.getTomail() == null || req.getSubject() == null || req.getBody() == null) {
            return ResponseEntity.badRequest().body(new NotificationResponse("FAILED", "Invalid request payload"));
        }
        boolean sent = notificationService.sendEmail(req.getTomail(), req.getSubject(), req.getBody());
        if (sent) {
            return ResponseEntity.ok(new NotificationResponse("SUCCESS", "Email sent successfully"));
        } else {
            return ResponseEntity.status(500).body(new NotificationResponse("FAILED", "Unable to send email"));
        }
    }
}
