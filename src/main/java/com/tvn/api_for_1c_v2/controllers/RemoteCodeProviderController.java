package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.exceptions.ForbiddenException;
import com.tvn.api_for_1c_v2.exceptions.NotFountException;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.ClientService;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.FunctionService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
public class RemoteCodeProviderController {
    private ClientService clientService;
    private FunctionService functionService;

    @GetMapping(value = "/remoteCodeProvider/{licenseKey}/{hardwareHashCode}/{handlerName}/{functionName}/{handlerVersion}")
    public Map<String, String> getHandlerFunctionCode(@PathVariable String licenseKey,
                                      @PathVariable String hardwareHashCode,
                                      @PathVariable String handlerName,
                                      @PathVariable String functionName,
                                      @PathVariable String handlerVersion) throws NotFountException, ForbiddenException {
        Map<String, String> answerMap = new LinkedHashMap<>();
        answerMap.put("handlerName", handlerName);
        answerMap.put("functionName", functionName);

        Client currentClient = clientService.findByLicenseKey(licenseKey);

        if (!clientService.isLicenseKeyActive(currentClient)) {
            throw new ForbiddenException();
        }
        if (!clientService.isHardwareHashCodeMatch(hardwareHashCode, currentClient.getHardwareHashCode())){
            //TODO need to add an incident alert
            throw new ForbiddenException();
        }

        Function function = functionService.findFunctionByNameAndHandlerNameVersionClient(functionName, handlerName, handlerVersion,currentClient);

        answerMap.put("code", function.getCode());

        return answerMap;
    }

}
