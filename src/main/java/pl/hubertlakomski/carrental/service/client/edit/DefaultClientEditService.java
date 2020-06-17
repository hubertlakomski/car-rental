package pl.hubertlakomski.carrental.service.client.edit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.repository.AddressRepository;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;


@Service
@RequiredArgsConstructor
public class DefaultClientEditService implements ClientEditService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Override
    public void updateClient(ClientEditData clientEditData) {

        Client clientFromDb = clientRepository.getById(clientEditData.getId());

        clientFromDb.setFirstName(clientEditData.getFirstName());
        clientFromDb.setLastName(clientEditData.getLastName());
        clientFromDb.setEmail(clientEditData.getEmail());
        clientFromDb.setNumberOfDrivingLicense(clientEditData.getNumberOfDrivingLicence());
        clientFromDb.setNumberOfId(clientEditData.getNumberOfId());

        Address addressFromDb = clientFromDb.getAddress();

        addressFromDb.setZipCode(clientEditData.getZippCode());
        addressFromDb.setCountry(clientEditData.getCountry());
        addressFromDb.setCity(clientEditData.getCity());
        addressFromDb.setAddressLine(clientEditData.getAddressLine());

        addressRepository.save(addressFromDb);

        clientRepository.save(clientFromDb);
    }
}
