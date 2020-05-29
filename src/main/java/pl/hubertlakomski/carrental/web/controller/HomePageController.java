package pl.hubertlakomski.carrental.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.hubertlakomski.carrental.domain.model.Rental;
import pl.hubertlakomski.carrental.domain.repository.RentalRepository;
import pl.hubertlakomski.carrental.service.homepage.HomePageService;
import pl.hubertlakomski.carrental.utils.SecurityUtils;

@Controller
@SessionAttributes("rental_config")
@RequestMapping("/")
public class HomePageController {

    private final HomePageService homePageService;
    private final RentalRepository rentalRepository;

    public HomePageController(HomePageService homePageService, RentalRepository rentalRepository) {
        this.homePageService = homePageService;
        this.rentalRepository = rentalRepository;
    }

    @GetMapping
    public String prepareHomePage(Model model){

        model.addAttribute("username", SecurityUtils.username());
        model.addAttribute("homePageData", homePageService.data());

        return "home-page";
    }

    @ModelAttribute("rental_config")
    public Rental getData(){

        return rentalRepository.findFirstBy();
    }
}
