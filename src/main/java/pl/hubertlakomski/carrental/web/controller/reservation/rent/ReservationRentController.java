package pl.hubertlakomski.carrental.web.controller.reservation.rent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.reservations.rent.ReservationRentData;
import pl.hubertlakomski.carrental.service.reservations.rent.ReservationRentService;

@Controller
@RequestMapping("reservations/rentProcess")
public class ReservationRentController {

    private final ReservationRentService reservationRentService;

    public ReservationRentController(ReservationRentService reservationRentService) {
        this.reservationRentService = reservationRentService;
    }

    @GetMapping
    public String prepareRentProcessPage(@RequestParam Long id, Model model){

        ReservationRentData reservationRentData =
                reservationRentService.prepareData(id);

        model.addAttribute("data", reservationRentData);

        return "reservation/rent/page";
    }


}
