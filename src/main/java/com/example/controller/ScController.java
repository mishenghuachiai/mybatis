package com.example.controller;

import com.example.config.AgentSpecialConfig;
import com.example.entity.Person;
import com.example.service.ScService;
import com.example.service.UserServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ScController {

    @Value("${http_url:192.168.99.100}")
    private String httpUrl;
    @Value("${name}")
    private String name;
    @Autowired
    ScService scService;
    @Autowired
    private AgentSpecialConfig agentSpecialConfig;
    @RequestMapping("/home/{id}")
    public Person test(@PathVariable int id) {
        System.out.println(httpUrl);
        System.out.println(name);
        agentSpecialConfig.checkAgentSpecialConfig("http_url");
        Person person = new Person();
        person.setName(name);
        return person;
    }
}
