package com.tvn.api_for_1c_v2.persistence.dao.repositories;

import com.tvn.api_for_1c_v2.persistence.model.Client;
import com.tvn.api_for_1c_v2.persistence.model.Function;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FunctionRepository extends CrudRepository<Function, Long> {

    Optional<Function> findFirstByNameAndHandler_NameAndHandler_VersionAndHandler_Clients(String name, String handler_name, String handler_version, Client clients);

    List<Function> findAllByHandler_IdOrderByNameAsc(Long id);

}
