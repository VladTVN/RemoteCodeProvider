package com.tvn.api_for_1c_v2.persistence.dao.services.interfaces;

import com.tvn.api_for_1c_v2.exceptions.NotFountException;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    Client findByLicenseKey(String licenseKey) throws NotFountException;

    boolean isLicenseKeyActive(Client client);
    boolean isHardwareHashCodeMatch(String hardwareHashCode, String hardwareHashCodeDB);

}
