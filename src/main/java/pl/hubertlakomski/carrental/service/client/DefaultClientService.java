package pl.hubertlakomski.carrental.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.repository.AddressRepository;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;


@Service
@RequiredArgsConstructor
public class DefaultClientService implements ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Override
    public void updateClient(ClientData clientData) {

        Client clientFromDb = clientRepository.getById(clientData.getId());

        clientFromDb.setFirstName(clientData.getFirstName());
        clientFromDb.setLastName(clientData.getLastName());
        clientFromDb.setEmail(clientData.getEmail());
        clientFromDb.setNumberOfDrivingLicense(clientData.getNumberOfDrivingLicence());
        clientFromDb.setNumberOfId(clientData.getNumberOfId());

        Address addressFromDb = clientFromDb.getAddress();

        addressFromDb.setZipCode(clientData.getZippCode());
        addressFromDb.setCountry(clientData.getCountry());
        addressFromDb.setCity(clientData.getCity());
        addressFromDb.setAddressLine(clientData.getAddressLine());

        addressRepository.save(addressFromDb);

        clientRepository.save(clientFromDb);
    }
}
