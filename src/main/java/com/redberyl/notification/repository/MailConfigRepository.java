package com.redberyl.notification.repository;

import com.redberyl.notification.entity.MailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailConfigRepository extends JpaRepository<MailConfig, Long> {
    MailConfig findTopByOrderByIdDesc();
}
