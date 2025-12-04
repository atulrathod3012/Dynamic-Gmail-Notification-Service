package com.redberyl.notification.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mail_config")
public class MailConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String protocol;
    @Column(columnDefinition = "TEXT")
    private String properties; //
}
