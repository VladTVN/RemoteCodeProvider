package com.tvn.api_for_1c_v2.persistence.dao.services.interfaces;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.model.Client;


public interface ClientService {
    Client findByLicenseKey(String licenseKey) throws NotFoundException;

    boolean isLicenseKeyActive(Client client);
    boolean isHardwareHashCodeMatch(String hardwareHashCode, String hardwareHashCodeDB);

}
