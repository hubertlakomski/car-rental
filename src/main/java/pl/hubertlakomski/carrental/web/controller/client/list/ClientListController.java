package pl.hubertlakomski.carrental.web.controller.client.list;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.service.client.list.ClientListService;

@Controller
@RequestMapping("/clients")
public class ClientListController {

    private final ClientListService clientListService;

    public ClientListController(ClientListService clientListService) {
        this.clientListService = clientListService;
    }


    @GetMapping
    public String prepareClientListPage(Model model){

        model.addAttribute("dataList", clientListService.getClients());

        return "client/list/page";
    }


}
