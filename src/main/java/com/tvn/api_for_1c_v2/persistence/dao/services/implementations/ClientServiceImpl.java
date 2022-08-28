package com.tvn.api_for_1c_v2.persistence.dao.services.implementations;

import com.tvn.api_for_1c_v2.exceptions.NotFountException;
import com.tvn.api_for_1c_v2.persistence.dao.repositories.ClientRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.ClientService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Override
    public Client findByLicenseKey(String licenseKey) throws NotFountException {
        Optional<Client> clientOptional = clientRepository.findByLicenseKey(licenseKey);

        return clientOptional.orElseThrow(NotFountException::new);

    }

    @Override
    public boolean isLicenseKeyActive(Client client) {
        Date keyExpirationDate = client.getKeyExpirationDate();
        Date currantDate = new Date(System.currentTimeMillis());

        return keyExpirationDate.after(currantDate);
    }

    @Override
    public boolean isHardwareHashCodeMatch(String hardwareHashCode, String hardwareHashCodeDB) {
        return hardwareHashCode.equals(hardwareHashCodeDB);
    }
}
