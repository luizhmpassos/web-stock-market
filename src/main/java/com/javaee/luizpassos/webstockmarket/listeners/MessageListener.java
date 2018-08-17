package com.javaee.luizpassos.webstockmarket.listeners;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.javaee.luizpassos.webstockmarket.api.v1.mapper.AcaoMapper;
import com.javaee.luizpassos.webstockmarket.config.RabbitMQConfig;
import com.javaee.luizpassos.webstockmarket.domain.Message;
import com.javaee.luizpassos.webstockmarket.repositories.AcaoRepository;
import com.javaee.luizpassos.webstockmarket.services.AcaoService;
import com.javaee.luizpassos.webstockmarket.services.AcaoServiceImpl;

@Component
public class MessageListener {

	static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	private String base_url = "http://localhost:8080/api/v1/acoes/transfer/";
	

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MESSAGES)
    public void processMessage(Message message) {
    	    	
    	System.out.println("Processando Mensagem");
		
    	System.out.println("Processando Mensagem: " + message.getSubject().toString() + " - " + 
    	message.getBody().toString());
    			
    	System.out.println("Processando Mensagem: " + message.getAcaoId().toString() + " - " + 
    	    	message.getCompradorId().toString()); 	
    	
        logger.info("Message Received");
        //logger.info("Acao:" + message.getAcaoId());
        //logger.info("Novo Comprador:" + message.getCompradorId());
        
        
        try {
			post(message);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

    
    
    
    private void post(Message message) throws UnsupportedEncodingException {
	//public void sendPostRequest() throws UnsupportedEncodingException {
	    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	    
	    HttpPut putRequest = new HttpPut(this.base_url + message.getAcaoId());
	    
	    String payload = "{\"comprador\":" + message.getCompradorId().toString() + "}";
	    
	    //Gson gson = new Gson();	 
	    //String json = gson.toJson(message);
	    
	    putRequest.setEntity(new StringEntity(payload));
	    putRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
	    
	 
	    try (CloseableHttpResponse httpResponse = httpClient.execute(putRequest)) {
	        String content = EntityUtils.toString(httpResponse.getEntity());
	 
	        int statusCode = httpResponse.getStatusLine().getStatusCode();
	        System.out.println("statusCode = " + statusCode);
	        System.out.println("content = " + content);
	    } catch (IOException e) {
	        //handle exception
	        e.printStackTrace();
	    }
	    
    }
	
}