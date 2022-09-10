package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class AdministrationController {
    private HandlerService handlerService;

    @GetMapping("/handler")
    public String allHandlers(Model model){

        Map<String, List<Handler>> handlerMap = handlerService.getMapHandlerNameHandler();

        model.addAttribute("handlerMap", handlerMap);

        return "handlerList";
    }


}
