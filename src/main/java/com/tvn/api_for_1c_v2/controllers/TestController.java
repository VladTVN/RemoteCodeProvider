package com.tvn.api_for_1c_v2.controllers;

import com.tvn.api_for_1c_v2.exceptions.NotFountException;
import com.tvn.api_for_1c_v2.persistence.dao.repositories.ClientRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.FunctionService;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class TestController {
    private FunctionService functionService;
    private HandlerService handlerService;
    private ClientRepository clientRepository;

    @GetMapping(value = "/hi")
    public String test() throws NotFountException {
//        handlerService.addHandler(new Handler("test", "1.0"));
//        Date date = Date.valueOf("2022-08-11");
//
//        Handler handler = new Handler("1","1");
//        List<Handler> list =  new ArrayList<>();
//        list.add(handler);

//        Client client = new Client("1","1","1", date, date,list);

//        Optional<Client> clientList = clientRepository.findById(1l);
//       Client client = clientList.get();
//
//         Function function = functionService.findFunctionByNameAndHandlerNameVersionClient("1", "1", "1", client);
         return "test";
    }
}
