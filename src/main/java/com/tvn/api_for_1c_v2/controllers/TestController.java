package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.persistence.dao.services.implementations.FunctionServiceImpl;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.FunctionService;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class TestController {
    private FunctionService functionService;
    private HandlerService handlerService;

    @GetMapping(value = "/hi")
    public String test(){
        handlerService.addHandler(new Handler("test", "1.0"));
         List<Handler> list = handlerService.findAll();
         return list.toString();
    }
}
