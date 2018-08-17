package com.javaee.luizpassos.webstockmarket.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.luizpassos.webstockmarket.domain.Message;
import com.javaee.luizpassos.webstockmarket.services.MessageService;

@RestController
@RequestMapping(MessageController.BASE_URL)
public class MessageController {

	public static final String BASE_URL = "/api/v1/messages";
	
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewTransaction(@RequestBody Message message){
		messageService.sendMessage(message);
        return "Message sent";
    }
}