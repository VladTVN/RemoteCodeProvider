package com.tvn.api_for_1c_v2.persistence.dao.repositories;

import com.tvn.api_for_1c_v2.persistence.model.Handler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandlerRepository extends CrudRepository<Handler, Long> {
}
