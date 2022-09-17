package com.tvn.api_for_1c_v2.persistence.dao.services.interfaces;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.model.Handler;

import java.util.List;
import java.util.Map;

public interface HandlerService {
    List<Handler> findAll();
    void addHandler(String name, String version);

    Handler findHandlerByNameAndVersion(String name, String version);
    List<Handler> findAllByOrderByNameAsc();
    Map<String, List<Handler>> getMapHandlerNameHandler();
    Handler findById(Long id) throws NotFoundException;
}
