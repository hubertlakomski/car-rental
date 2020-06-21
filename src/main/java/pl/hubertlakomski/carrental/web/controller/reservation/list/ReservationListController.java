package pl.hubertlakomski.carrental.web.controller.reservation.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.service.reservations.add.ReservationAddData;
import pl.hubertlakomski.carrental.service.reservations.add.ReservationAddService;
import pl.hubertlakomski.carrental.service.reservations.edit.ReservationEditData;
import pl.hubertlakomski.carrental.service.reservations.edit.ReservationEditService;
import pl.hubertlakomski.carrental.service.reservations.list.ReservationListService;

import javax.validation.Valid;

@Controller
@RequestMapping({"/reservations", "/"})
@RequiredArgsConstructor
public class ReservationListController {

    private final ReservationListService reservationListService;
    private final ReservationAddService reservationAddService;
    private final ReservationEditService reservationEditService;

    @GetMapping
    public String prepareListPage(Model model){

        model.addAttribute("reservations", reservationListService.getReservations());
        model.addAttribute("departments", reservationListService.getDepartments());
        model.addAttribute("sippcodes", reservationListService.getSippCodes());
        model.addAttribute("clients", reservationListService.getClients());

        return "reservation/list/page";
    }

    @PostMapping(params={"add"})
    public String addReservationForm(@Valid ReservationAddData reservationAddData, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "reservation/list/page";
        }

        reservationAddService.processAddReservation(reservationAddData);

        return "redirect:/reservations";
    }

    @PostMapping(params={"edit", "id"})
    public String editReservationForm(@Valid ReservationEditData reservationEditData,
                                    BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "reservation/list/page";
        }

        reservationEditService.processEditReservation(reservationEditData);


        return "redirect:/reservations";
    }

}
