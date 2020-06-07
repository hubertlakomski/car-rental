package pl.hubertlakomski.carrental.web.controller.reservation.rent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.reservations.rent.ReservationRentCarData;
import pl.hubertlakomski.carrental.service.reservations.rent.ReservationRentData;
import pl.hubertlakomski.carrental.service.reservations.rent.ReservationRentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/reservations/rent")
public class ReservationRentController {

    private final ReservationRentService reservationRentService;

    public ReservationRentController(ReservationRentService reservationRentService) {
        this.reservationRentService = reservationRentService;
    }

    @GetMapping
    public String prepareRentReservationPage(@RequestParam Long reservationId, Model model){

        ReservationRentData reservationRentData =
                reservationRentService.prepareData(reservationId);

        List<ReservationRentCarData> availableCarsInDepartment =
                reservationRentService.getAvailableCarsInDepartment(reservationId);

        model.addAttribute("reservationRentData", reservationRentData);
        model.addAttribute("availableCarsInDepartment", availableCarsInDepartment);

        return "reservation/rent/page";
    }

    @PostMapping
    public String processRentReservationPage(@Valid ReservationRentData data,
                                             BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "reservation/rent/page";
        }

        reservationRentService.processData(data);

        return "redirect:/reservation?id="+data.getReservationId();
    }


}
