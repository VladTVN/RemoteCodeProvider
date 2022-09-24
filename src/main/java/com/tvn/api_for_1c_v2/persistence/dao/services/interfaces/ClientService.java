package com.tvn.api_for_1c_v2.persistence.dao.services.interfaces;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;


public interface ClientService {
    Client findByLicenseKey(String licenseKey) throws NotFoundException;

    boolean isLicenseKeyActive(Client client);
    boolean isHardwareHashCodeMatch(String hardwareHashCode, String hardwareHashCodeDB);
    List<Client> findAll();
    boolean addClient(String name, String  hardwareHashCode, Date keyRegistrationDate, Date keyExpirationDate);

    Client findByName(String name) throws NotFoundException;

    List<Client> findAllByNameContaining(String filter);
}
