package com.tvn.api_for_1c_v2.persistence.dao.services.implementations;

import com.tvn.api_for_1c_v2.persistence.dao.repositories.FunctionRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.FunctionService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class FunctionServiceImpl implements FunctionService {
    private FunctionRepository functionRepository;

    @Override
    public Function findFunctionByNameAndHandlerNameVersionClient(String functionName, String handlerName, String handlerVersion, Client client) {
        return functionRepository.findFirstByNameAndHandler_NameAndHandler_VersionAndHandler_Clients(functionName, handlerName, handlerVersion, client);
    }


// -----------------------------------------   For Test

    @Override
    public String findAll() {
        return functionRepository.findAll().toString();
    }

    @Override
    public void addFunction(String name, String code, Handler handler) {
        Function function = new Function(name, code, new Handler());

        functionRepository.save(function);
    }


}
