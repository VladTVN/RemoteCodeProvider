package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.FunctionService;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class AdministrationController {
    private HandlerService handlerService;
    private FunctionService functionService;

    @GetMapping("/handler")
    public String allHandlers(Model model){

        Map<String, List<Handler>> handlerMap = handlerService.getMapHandlerNameHandler();

        model.addAttribute("handlerMap", handlerMap);

        return "handlerList";
    }

    @GetMapping("/handler/{id}")
    public String handlerFunctions(@PathVariable Long id, Model model){

        List<Function> functionList = functionService.findHandlerFunctionsByHandlerId(id);
        if (!model.containsAttribute("operationStatus")) {
            model.addAttribute("operationStatus", "");
        }
        model.addAttribute("functionList", functionList);
        model.addAttribute("handlerId", id);

        return "functionList";
    }

    @PostMapping("/updateFunction/{handlerId}")
    public String updateHandlerFunction(@PathVariable Long handlerId,
                                        @RequestParam("functionId") Long functionId,
                                        @RequestParam("functionCode") String functionCode, Model model, RedirectAttributes atr){

        try {
            functionService.updateFunction(functionId, functionCode);
            atr.addFlashAttribute("operationStatus", "Function has been successfully updated");
        } catch (NotFoundException e) {
            atr.addFlashAttribute("operationStatus", "A server processing error has occurred. Feature has not been updated");
        }

        return "redirect:/handler/" + handlerId;
    }


}
