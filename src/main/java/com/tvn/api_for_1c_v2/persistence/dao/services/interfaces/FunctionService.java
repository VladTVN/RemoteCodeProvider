package com.tvn.api_for_1c_v2.persistence.dao.services.interfaces;

import com.tvn.api_for_1c_v2.exceptions.NotFountException;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import com.tvn.api_for_1c_v2.persistence.model.Handler;

import java.util.Optional;

public interface FunctionService {
    String findAll();
    void addFunction(String name, String code, Handler handler);

    Function findFunctionByNameAndHandlerNameVersionClient(String functionName, String handlerName, String handlerVersion, Client client) throws NotFountException;
}
