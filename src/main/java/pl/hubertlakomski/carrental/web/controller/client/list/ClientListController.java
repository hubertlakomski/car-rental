package pl.hubertlakomski.carrental.web.controller.client.list;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.service.client.add.ClientAddData;
import pl.hubertlakomski.carrental.service.client.add.ClientAddService;
import pl.hubertlakomski.carrental.service.client.edit.ClientEditData;
import pl.hubertlakomski.carrental.service.client.edit.ClientEditService;

import pl.hubertlakomski.carrental.service.client.list.ClientListService;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientListController {

    private final ClientListService clientListService;
    private final ClientEditService clientEditService;
    private final ClientAddService clientAddService;

    @GetMapping
    public String prepareClientListPage(Model model){

        model.addAttribute("dataList", clientListService.getClients());

        return "client/list/page";
    }

    @PostMapping(params={"edit", "id"})
    public String editClientDetails(@Valid ClientEditData clientEditData,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "client/list/page";
        }

        clientEditService.updateClient(clientEditData);


        return "redirect:/clients";
    }

    @PostMapping(params={"add"})
    public String editClientDetails(@Valid ClientAddData clientAddData,
                                    BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "client/list/page";
        }

        clientAddService.addClient(clientAddData);


        return "redirect:/clients";
    }


}
