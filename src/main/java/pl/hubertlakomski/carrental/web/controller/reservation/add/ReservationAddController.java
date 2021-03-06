package pl.hubertlakomski.carrental.web.controller.reservation.add;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.service.reservations.add.ReservationAddData;
import pl.hubertlakomski.carrental.service.reservations.add.ReservationAddService;

import javax.validation.Valid;

@Controller
@RequestMapping("/reservation/add")
@Slf4j
@RequiredArgsConstructor
public class ReservationAddController {

    private final ReservationAddService reservationAddService;

    @GetMapping
    public String prepareAddPage(Model model){

        model.addAttribute("departments", reservationAddService.getDepartments());
        model.addAttribute("clients", reservationAddService.getClients());
        model.addAttribute("sippCodes", reservationAddService.getSippCodes());
        model.addAttribute(new ReservationAddData());

        return "reservation/add/page";
    }

    @PostMapping
    public String processAddPage(@Valid ReservationAddData data, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "reservation/add/page";
        }

        log.info(data.getComment());

        Long reservationId = reservationAddService.processAddReservation(data);

        return "redirect:/reservation?id="+reservationId;
    }

}
