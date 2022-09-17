package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.FunctionService;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
public class FunctionController {
    private HandlerService handlerService;
    private FunctionService functionService;


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
                                        @RequestParam("functionCode") String functionCode, RedirectAttributes atr){

        try {
            functionService.updateFunction(functionId, functionCode);
            atr.addFlashAttribute("operationStatus", "Function has been successfully updated");
        } catch (NotFoundException e) {
            atr.addFlashAttribute("operationStatus", "A server processing error has occurred. Function has not been updated");
        }

        return "redirect:/handler/" + handlerId;
    }

    @PostMapping("/addFunction/{handlerId}")
    public String addHandlerFunction(@PathVariable Long handlerId,
                                     @RequestParam("functionName") String functionName,
                                     @RequestParam("functionCode") String functionCode, RedirectAttributes atr){

        try {
            Handler handler = handlerService.findById(handlerId);
            functionService.addFunction(functionName, functionCode, handler);
            atr.addFlashAttribute("operationStatus", "Function has been successfully added");
        } catch (NotFoundException e){
            atr.addFlashAttribute("operationStatus", "A server processing error has occurred. Function has not been added");
        }

        return "redirect:/handler/" + handlerId;
    }


}
