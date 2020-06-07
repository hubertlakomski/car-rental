package pl.hubertlakomski.carrental.web.controller.client.list;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;
import pl.hubertlakomski.carrental.service.client.ClientData;
import pl.hubertlakomski.carrental.service.client.ClientService;

import pl.hubertlakomski.carrental.service.client.list.ClientListService;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientListController {

    private final ClientListService clientListService;
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    @GetMapping
    public String prepareClientListPage(Model model){

        model.addAttribute("dataList", clientListService.getClients());

        return "client/list/page";
    }

    @PostMapping(params={"edit", "id"})
    public String editClientDetails(@Valid ClientData clientData,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "client/list/page";
        }

        clientService.updateClient(clientData);


        return "redirect:/clients";
    }


}
