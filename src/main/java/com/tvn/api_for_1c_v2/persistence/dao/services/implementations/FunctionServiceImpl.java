package com.tvn.api_for_1c_v2.persistence.dao.services.implementations;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.dao.repositories.FunctionRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.FunctionService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class FunctionServiceImpl implements FunctionService {
    private FunctionRepository functionRepository;

    @Override
    public Function findFunctionByNameAndHandlerNameVersionClient(String functionName, String handlerName, String handlerVersion, Client client) throws NotFoundException {

        return functionRepository
                .findFirstByNameAndHandler_NameAndHandler_VersionAndHandler_Clients(functionName, handlerName, handlerVersion, client)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Function> findHandlerFunctionsByHandlerId(Long id) {
        List<Function> functionList = functionRepository.findAllByHandler_IdOrderByNameAsc(id);
        if (functionList == null){
            return new ArrayList<>();
        }

        return functionList;
    }

    @Override
    public List<Function> findHandlerFunctionsByHandlerIdAndFunctionName(Long id, String name) {
        return functionRepository.findAllByHandler_IdAndNameContainingIgnoreCase(id, name.toLowerCase());
    }

    @Override
    public String findAll() {
        return functionRepository.findAll().toString();
    }

    @Override
    public void addFunction(String name, String code, Handler handler) {
        Function function = new Function(name, code, handler);
        functionRepository.save(function);
    }

    @Override
    public void updateFunction(Long id, String functionCode) throws NotFoundException {
        Function function = functionRepository.findById(id).orElseThrow(NotFoundException::new);

        function.setCode(functionCode);
        functionRepository.save(function);

    }




}
