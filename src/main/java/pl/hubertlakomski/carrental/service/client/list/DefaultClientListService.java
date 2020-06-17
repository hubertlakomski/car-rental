package pl.hubertlakomski.carrental.service.client.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultClientListService implements ClientListService {


    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public List<ClientListData> getClients() {

        List<ClientListData> data = new ArrayList<>();
        List<Client> clients = clientRepository.findAll();

        for(Client client: clients){

            ClientListData clientListData = new ClientListData();

            Address clientAddress = client.getAddress();

            clientListData.setId(client.getId());
            clientListData.setEmail(client.getEmail());
            clientListData.setFirstName(client.getFirstName());
            clientListData.setLastName(client.getLastName());
            clientListData.setNumberOfDrivingLicence(client.getNumberOfDrivingLicense());
            clientListData.setNumberOfId(client.getNumberOfId());

            if(clientAddress!=null){
                clientListData.setCountry(clientAddress.getCountry());
                clientListData.setCity(clientAddress.getCity());
                clientListData.setAddressLine(clientAddress.getAddressLine());
                clientListData.setZippCode(clientAddress.getZipCode());
            }

            data.add(clientListData);
        }

        return data;
    }
}
