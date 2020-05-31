package pl.hubertlakomski.carrental.web.controller.reservation.rent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.reservations.rent.ReservationRentData;
import pl.hubertlakomski.carrental.service.reservations.rent.ReservationRentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/reservations/rent")
public class ReservationRentController {

    private final ReservationRentService reservationRentService;

    public ReservationRentController(ReservationRentService reservationRentService) {
        this.reservationRentService = reservationRentService;
    }

    @GetMapping
    public String prepareRentCarPage(@RequestParam Long id, Model model){

        ReservationRentData reservationRentData =
                reservationRentService.prepareData(id);

        model.addAttribute("data", reservationRentData);

        return "reservation/rent/page";
    }

    @PostMapping
    public String processRentCarPage(@Valid ReservationRentData reservationRentData,
                                     BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "reservation/rent/page";
        }

        reservationRentService.processData(reservationRentData);

        return "redirect:/reservations?id="+reservationRentData.getReservationId();
    }


}
