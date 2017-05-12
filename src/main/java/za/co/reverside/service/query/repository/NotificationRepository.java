package za.co.reverside.service.query.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import za.co.reverside.service.domain.model.Notification;


public interface NotificationRepository extends MongoRepository<Notification, String>{

}
