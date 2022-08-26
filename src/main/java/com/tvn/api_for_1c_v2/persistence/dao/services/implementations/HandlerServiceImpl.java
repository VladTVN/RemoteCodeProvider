package com.tvn.api_for_1c_v2.persistence.dao.services.implementations;

import com.tvn.api_for_1c_v2.persistence.dao.repositories.HandlerRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.HandlerService;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HandlerServiceImpl implements HandlerService {
    private HandlerRepository handlerRepository;

    @Override
    public List<Handler> findAll() {
        return (List<Handler>) handlerRepository.findAll();
    }

    @Override
    public void addHandler(Handler handler) {
        handlerRepository.save(handler);
    }
}
