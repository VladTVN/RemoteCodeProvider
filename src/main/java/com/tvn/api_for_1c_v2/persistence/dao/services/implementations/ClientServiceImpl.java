package com.tvn.api_for_1c_v2.persistence.dao.services.implementations;

import com.tvn.api_for_1c_v2.exceptions.NotFoundException;
import com.tvn.api_for_1c_v2.persistence.dao.repositories.ClientRepository;
import com.tvn.api_for_1c_v2.persistence.dao.services.interfaces.ClientService;
import com.tvn.api_for_1c_v2.persistence.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Override
    public Client findByLicenseKey(String licenseKey) throws NotFoundException {
        Optional<Client> clientOptional = clientRepository.findByLicenseKey(licenseKey);

        return clientOptional.orElseThrow(NotFoundException::new);

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

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public boolean addClient(String name, String  hardwareHashCode, Date keyRegistrationDate, Date keyExpirationDate) {
        String licenseKey = UUID.randomUUID().toString();

        try {
            findByName(name);
            return false;
        } catch (NotFoundException e) {
            Client client = new Client(name, hardwareHashCode, licenseKey, keyRegistrationDate, keyExpirationDate);
            clientRepository.save(client);
        }
        return true;
    }

    @Override
    public Client findByName(String name) throws NotFoundException {
        return clientRepository.findByName(name).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Client> findAllByNameContaining(String filter) {
        return clientRepository.findAllByNameContainingIgnoreCase(filter.toLowerCase());
    }

    @Override
    public Client findById(Long id) throws NotFoundException {
        return clientRepository.findById(id).orElseThrow(NotFoundException::new);
    }


}
