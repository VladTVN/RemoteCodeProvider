package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.ClientService;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Controller
public class ClientController {
    private ClientService clientService;
    private HandlerService handlerService;

    @GetMapping("/client")
    public String allClients(@RequestParam(value = "filter",required = false, defaultValue = "")String filter, Model model){
        List<Client> clientList;
        if (filter.isEmpty()) {
            clientList = clientService.findAll();
        }else{
            clientList = clientService.findAllByNameContaining(filter);
        }
        model.addAttribute("clientList", clientList);
        if (!model.containsAttribute("operationStatus")) {
            model.addAttribute("operationStatus", "");
        }

        return "clientList";

    }

    @GetMapping("/client/{id}")
    public String clientAdmin(@RequestParam(required = false, defaultValue = "", value = "filter") String filter,
                              @PathVariable(value = "id") Long id,
                              Model model) throws NotFoundException {

        List<Client> clientList = new ArrayList<>();
        Client client = clientService.findById(id);
        clientList.add(client);

        Map<String, List<Handler>> handlerMap = handlerService.getClientsMapHandlerNameHandler(filter, clientList);
        Map<String, List<Handler>> handlerMapNoAdded = handlerService.findHandlersWhereClientsNotIn(filter, clientList);

        if (!model.containsAttribute("operationStatus")) {
            model.addAttribute("operationStatus", "");
        }

        model.addAttribute("handlerMapNoAdded", handlerMapNoAdded);
        model.addAttribute("handlerMap", handlerMap);
        model.addAttribute("clientId", id);

        return "clientHandlerList";
    }

    @PostMapping("/addClient")
    public String addClient(@RequestParam("name") String name,
                            @RequestParam("hardwareHashCode") String hardwareHashCode,
                            @RequestParam("keyRegistrationDate") Date keyRegistrationDate,
                            @RequestParam("keyExpirationDate")Date keyExpirationDate, RedirectAttributes redirectAttributes){

       boolean result = clientService.addClient(name, hardwareHashCode, keyRegistrationDate, keyExpirationDate);

       if (result){
           redirectAttributes.addFlashAttribute("operationStatus","Client successfully added");
       }else {
           redirectAttributes.addFlashAttribute("operationStatus","A client with the same name already exists");
       }

        return "redirect:/client";

    }


    @PostMapping("/addHandlerToClient/{clientId}")
    public String addHandlerToClient(@PathVariable(name = "clientId") Long clientId, @RequestParam(name = "handlerId") Long handlerId, RedirectAttributes redirectAttributes) throws NotFoundException {

        Client client = clientService.findById(clientId);
        Handler handler = handlerService.findById(handlerId);

        if (!client.getAvailableHandlers().contains(handler)) {
            client.getAvailableHandlers().add(handler);
            clientService.updateClient(client);
            redirectAttributes.addFlashAttribute("operationStatus","Handler successfully added to client");
        }else{
            redirectAttributes.addFlashAttribute("operationStatus","The client already has access to the handler");
        }

        return "redirect:/client/"+clientId;
    }

    @PostMapping("/deleteHandlerToClient/{clientId}")
    public String deleteHandlerToClient(@PathVariable(name = "clientId") Long clientId, @RequestParam(name = "handlerId") Long handlerId, RedirectAttributes redirectAttributes) throws NotFoundException {

        Client client = clientService.findById(clientId);
        Handler handler = handlerService.findById(handlerId);

        if (client.getAvailableHandlers().contains(handler)) {
            client.getAvailableHandlers().remove(handler);
            clientService.updateClient(client);
            redirectAttributes.addFlashAttribute("operationStatus","The handler was successfully removed from the client");
        }else{
            redirectAttributes.addFlashAttribute("operationStatus","The client does not have access to this handler");
        }

        return "redirect:/client/"+clientId;
    }

}
