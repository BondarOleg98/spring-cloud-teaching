package com.olegb.client.Controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.logging.Logger;

@Controller
public class RabbitController {
    Logger logger = Logger.getLogger(String.valueOf(RabbitController.class));
    @Autowired
    AmqpTemplate template;

    @RequestMapping("/message")
    @ResponseBody
    public String proccessMessageQueue(){
        logger.info("Emit to queue");
        template.convertAndSend("HelloWorld","Hello world");
        return "Hello world";
    }
}
