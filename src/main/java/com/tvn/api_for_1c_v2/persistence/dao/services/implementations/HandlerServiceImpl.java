package com.tvn.api_for_1c_v2.persistence.dao.services.implementations;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.dao.repositories.HandlerRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class HandlerServiceImpl implements HandlerService {
    private HandlerRepository handlerRepository;

    @Override
    public Handler findHandlerByNameAndVersion(String name, String version) {
        return handlerRepository.findFirstByNameAndVersion(name, version);
    }

    @Override
    public List<Handler> findAll() {
        return (List<Handler>) handlerRepository.findAll();
    }

    @Override
    public void addHandler(String name, String version) {
        Handler handler = new Handler(name, version);
        handlerRepository.save(handler);
    }

    public List<Handler> findAllByOrderByNameAsc(){
        List<Handler> handlerList = handlerRepository.findAllByOrderByNameAsc();
        if (handlerList == null){
            return new ArrayList<>();
        }
        return handlerList;
    }

    public Map<String, List<Handler>> getMapHandlerNameHandler(String filter){

        List<Handler> handlerList;

        if(!filter.isEmpty()){
            handlerList = handlerRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(filter);
        }else {
            handlerList = handlerRepository.findAllByOrderByNameAsc();
        }

        return groupListIntoMap(handlerList);
    }

    private Map<String, List<Handler>> groupListIntoMap(List<Handler> handlerList) {
        if (handlerList == null){
            return new HashMap<>();
        }

        Map<String, List<Handler>> handlerMap = new HashMap<>();

        for(Handler handler: handlerList){
            String handlerName = handler.getName();
            if (handlerMap.containsKey(handlerName)){
                handlerMap.get(handlerName).add(handler);

            }else {
                List<Handler> newChainHandler = new ArrayList<>();
                newChainHandler.add(handler);

                handlerMap.put(handlerName, newChainHandler);
            }
        }
        return handlerMap;
    }

    @Override
    public Map<String, List<Handler>> getClientsMapHandlerNameHandler(String filter, List<Client> clients) {

        List<Handler> handlerList;
        if (filter.isEmpty()) {
            handlerList = handlerRepository.findAllByClientsIn(clients);
        }else {
            handlerList = handlerRepository.findAllByClientsInAndName(clients, filter);
        }

        return groupListIntoMap(handlerList);
    }

    @Override
    public Handler findById(Long id) throws NotFoundException {

        return handlerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Map<String, List<Handler>> findHandlersWhereClientsNotIn(String filter, List<Client> clientList) {
        List<Handler> handlerList;
        if (filter.isEmpty()) {
            handlerList = handlerRepository.findAllByClientsNotInOrClientsEmpty(clientList);
        }else {
            handlerList = handlerRepository.findAllByNameAndClientsNotInAndClients_Empty(filter, clientList);
        }

        return groupListIntoMap(handlerList);

    }


}
