package org.example.patternproject2.perositories;

import org.example.patternproject2.model.NotificationBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationBase,Long> {

}
