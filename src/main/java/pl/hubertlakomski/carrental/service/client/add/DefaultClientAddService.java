package pl.hubertlakomski.carrental.service.client.add;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.repository.AddressRepository;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class DefaultClientAddService implements ClientAddService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Override
    public void addClient(ClientAddData clientAddData) {

        Address address = new Address();

        address.setCountry(clientAddData.getCountry());
        address.setCity(clientAddData.getCity());
        address.setAddressLine(clientAddData.getAddressLine());
        address.setZipCode(clientAddData.getZippCode());

        addressRepository.save(address);

        Client client = new Client();

        client.setFirstName(clientAddData.getFirstName());
        client.setLastName(clientAddData.getLastName());
        client.setEmail(clientAddData.getEmail());
        client.setNumberOfId(clientAddData.getNumberOfId());
        client.setNumberOfDrivingLicense(clientAddData.getNumberOfDrivingLicence());
        client.setAddress(address);

        clientRepository.save(client);
    }


}
