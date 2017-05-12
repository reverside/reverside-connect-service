package za.co.reverside.service.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zenerick.core.mapper.Mapper;

import za.co.reverside.service.domain.model.Notification;
import za.co.reverside.service.query.model.Multiple;
import za.co.reverside.service.query.model.Single;
import za.co.reverside.service.query.repository.NotificationRepository;

@RestController("query")
public class Service {
	
	@Autowired
	private Mapper mapper;
	
	@Autowired
	NotificationRepository repository;
	
	@RequestMapping(path="/api/query/notifications", method=RequestMethod.GET, produces="application/json")
	public List<Multiple> findNotifications(){
	  List<Notification> notifications = repository.findAll();
	  List<Multiple> result = new ArrayList<Multiple>();
	  for(Notification notification : notifications){
		  Multiple item = mapper.map(notification, Multiple.class);
		  result.add(item);
	  }
	  return result;
	}
	
	@RequestMapping(path="/api/query/notifications/{id}", method=RequestMethod.GET, produces="application/json")
	public Single findNotification(@PathVariable("id") String id){
	  Notification notification = repository.findOne(id);
	  if(notification != null){
		  return mapper.map(notification, Single.class);  
	  } else {
		  throw new RuntimeException("Resource Not Found");
	  }
	}
	

}
