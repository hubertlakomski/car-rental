package pl.hubertlakomski.carrental.web.controller.client.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.client.details.ClientDetailsService;

@Controller
@RequestMapping("/client")
public class ClientDetailsController {

    private final ClientDetailsService clientDetailsService;

    public ClientDetailsController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @GetMapping
    public String prepareClientDetails(@RequestParam Long id, Model model){

        model.addAttribute("data",clientDetailsService.getClientData(id));

        return "clients/details/page";
    }

}
