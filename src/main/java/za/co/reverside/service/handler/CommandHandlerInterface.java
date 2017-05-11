package za.co.reverside.service.handler;

import za.co.reverside.service.command.Send;

public interface CommandHandlerInterface {

	public void handle(String user, String id, Send command) throws Exception;

}