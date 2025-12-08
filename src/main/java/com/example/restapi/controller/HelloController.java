package com.example.restapi.controller;


import com.example.restapi.dto.MessageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!!!!!!!!!!!!!!!!!!!!!!!!!!!!1";
    }

    @GetMapping("/api/message")
    public MessageResponse message() {
        return new MessageResponse("hello",200);
    }
    @GetMapping("/api/messages")
    public List<MessageResponse> messages() {
        return List.of(new MessageResponse("hello",200),new MessageResponse("world",300),new MessageResponse("user",400));
    }
}
