package com.tvn.api_for_1c_v2.persistence.dao.repositories;

import com.tvn.api_for_1c_v2.persistence.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByLicenseKey(String licenseKey);
    Optional<Client> findByName(String name);

    List<Client> findAllByNameContainingIgnoreCase(String name);
}
