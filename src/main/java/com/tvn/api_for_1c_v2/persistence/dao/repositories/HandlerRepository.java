package com.tvn.api_for_1c_v2.persistence.dao.repositories;

import com.tvn.api_for_1c_v2.persistence.model.Handler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HandlerRepository extends CrudRepository<Handler, Long> {
    Handler findFirstByNameAndVersion(String name, String version);
    List<Handler> findAllByOrderByNameAsc();

    Optional<Handler> findById(Long id);
}
