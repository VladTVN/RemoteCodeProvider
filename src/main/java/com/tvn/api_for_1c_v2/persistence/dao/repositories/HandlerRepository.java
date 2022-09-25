package com.tvn.api_for_1c_v2.persistence.dao.repositories;

import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Handler;
import org.springframework.data.repository.CrudRepository;
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

    List<Handler> findAllByClientsInAndIdNotIn(Collection<Client> clients, Set<Long> idSet);
}
