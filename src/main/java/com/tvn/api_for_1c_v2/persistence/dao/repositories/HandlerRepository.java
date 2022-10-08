package com.tvn.api_for_1c_v2.persistence.dao.repositories;

import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HandlerRepository extends CrudRepository<Handler, Long> {
    Handler findFirstByNameAndVersion(String name, String version);
    List<Handler> findAllByOrderByNameAsc();
    List<Handler> findAllByNameContainingIgnoreCaseOrderByNameAsc(String name);
    Optional<Handler> findById(Long id);
    List<Handler> findAllByClientsIn(Collection<Client> clients);
    List<Handler> findAllByClientsInAndName(Collection<Client> clients, String name);

    @Query(nativeQuery = true,value = "select * from Handler " +
            "left outer join client_handler ch on handler.id = ch.handler_id " +
            "left outer join client c on ch.client_id = c.id " +
            "where (not client_id in (:clients) " +
            "or client_id is null)" +
            "and handler.name like %:name%")
    List<Handler> findAllByNameAndClientsNotInAndClients_Empty(String name, Collection<Client> clients);
    List<Handler> findAllByClientsNotInOrClientsEmpty(Collection<Client> clients);
}
