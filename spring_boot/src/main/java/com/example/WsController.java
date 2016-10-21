package com.example;

import com.example.beans.WsMessage;
import com.example.beans.WsResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by zhougb on 2016/8/16.
 */

@Controller
public class WsController {
    private static final Log logger = LogFactory.getLog(WsController.class);

    @Autowired(required = false)
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WsResponse say(WsMessage wsMessage) throws Exception{
        Thread.sleep(300);

        logger.info("xxxxxxxxxxxxx  welcome xxxxxxxxxxxxx");
        return new WsResponse("Welcome, "+wsMessage.getName()+" !");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg){
        if (principal.getName().equals("wyf")){
            simpMessagingTemplate.convertAndSendToUser("zgb", "/queue/notifications", principal.getName()+"-send:"+msg);
        }else {
            simpMessagingTemplate.convertAndSendToUser("wyf", "/queue/notifications", principal.getName()+"-send:"+msg);
        }
    }



}
