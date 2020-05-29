package pl.hubertlakomski.carrental.service.client.details;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;

import javax.transaction.Transactional;

@Service
public class DefaultClientDetailsService implements ClientDetailsService {

    private final ClientRepository clientRepository;

    public DefaultClientDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @Override
    public ClientDetailsData getClientData(Long id) {

        ClientDetailsData data = new ClientDetailsData();

        Client client = clientRepository.getOne(id);

        data.setFirstName(client.getFirstName());
        data.setLastName(client.getLastName());
        data.setEmail(client.getEmail());
        data.setNumberOfDrivingLicense(client.getNumberOfDrivingLicense());
        data.setNumberOfId(client.getNumberOfId());

        data.setAddressId(client.getAddress().getId());

        data.setAddress(client.getAddress().getCountry()+
                ", "+client.getAddress().getZipCode()+" "+client.getAddress().getCity()+
                ", "+client.getAddress().getAddressLine());

        return data;
    }
}
