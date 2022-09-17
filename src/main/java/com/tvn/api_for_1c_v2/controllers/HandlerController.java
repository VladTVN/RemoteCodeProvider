package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class HandlerController {
    private HandlerService handlerService;

    @GetMapping("/handler")
    public String allHandlers(Model model){

        Map<String, List<Handler>> handlerMap = handlerService.getMapHandlerNameHandler();

        if (!model.containsAttribute("operationStatus")) {
            model.addAttribute("operationStatus", "");
        }

        model.addAttribute("handlerMap", handlerMap);

        return "handlerList";
    }

    @PostMapping("/addHandler")
    public String addHandler(@RequestParam("handlerName") String handlerName,
                             @RequestParam("handlerVersion") String handlerVersion,
                             RedirectAttributes atr){
        try {
            handlerService.addHandler(handlerName, handlerVersion);
            atr.addFlashAttribute("operationStatus", "Handler has been successfully added");
        }catch (Exception e){
            atr.addFlashAttribute("operationStatus", "A server processing error has occurred. Handler has not been added");
        }

        return "redirect:/handler";
    }

}
