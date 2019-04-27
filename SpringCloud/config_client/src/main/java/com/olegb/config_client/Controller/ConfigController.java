package com.olegb.config_client.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigController {
    @Value("${message:config_message}")
    private String message;

    @RequestMapping("/config")
    public String getStudentMessage() {
        return message;
    }
}
