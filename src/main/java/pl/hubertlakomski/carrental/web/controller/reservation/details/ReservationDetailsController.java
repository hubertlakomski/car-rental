package pl.hubertlakomski.carrental.web.controller.reservation.details;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.reservations.details.ReservationDetailsService;

@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationDetailsController {

    private final ReservationDetailsService reservationDetailsService;

    @GetMapping
    public String prepareReservationsListPage(@RequestParam Long id, Model model){

        model.addAttribute("data", reservationDetailsService.getReservationDetailsData(id));

        return "reservation/details/page";
    }

}
