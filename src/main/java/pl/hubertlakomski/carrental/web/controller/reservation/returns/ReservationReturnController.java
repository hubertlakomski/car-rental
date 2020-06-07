package pl.hubertlakomski.carrental.web.controller.reservation.returns;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.reservations.add.ReservationAddService;
import pl.hubertlakomski.carrental.service.reservations.returns.ReservationReturnData;
import pl.hubertlakomski.carrental.service.reservations.returns.ReservationReturnService;

import javax.validation.Valid;

@Controller
@RequestMapping("/reservations/return")
public class ReservationReturnController {

    private final ReservationReturnService reservationReturnService;
    private final ReservationAddService reservationAddService;

    public ReservationReturnController(ReservationReturnService reservationReturnService, ReservationAddService reservationAddService) {
        this.reservationReturnService = reservationReturnService;
        this.reservationAddService = reservationAddService;
    }

    @GetMapping
    public String prepareReturnReservationPage(@RequestParam Long reservationId, Model model){

        model.addAttribute("reservationReturnData", reservationReturnService.prepareData(reservationId));
        model.addAttribute("reservationData", reservationReturnService.getReservationData(reservationId));
        model.addAttribute("departments", reservationAddService.getDepartments());

        return "reservation/return/page";
    }

    @PostMapping
    public String processReturnReservationPage(@Valid ReservationReturnData data,
                                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "reservation/return/page";
        }

        reservationReturnService.processData(data);

        return "redirect:/reservation?id="+data.getReservationId();
    }


}
