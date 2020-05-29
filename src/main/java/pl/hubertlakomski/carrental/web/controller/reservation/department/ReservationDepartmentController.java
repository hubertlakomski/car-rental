package pl.hubertlakomski.carrental.web.controller.reservation.department;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.reservations.department.ReservationDepartmentService;

@Controller
@RequestMapping("/reservations/department")
public class ReservationDepartmentController {

    private final ReservationDepartmentService departmentReservationsService;

    public ReservationDepartmentController(ReservationDepartmentService departmentReservationsService) {
        this.departmentReservationsService = departmentReservationsService;
    }

    @GetMapping
    public String prepareDepartmentReservations(@RequestParam Long id, Model model){

        model.addAttribute("data",
                departmentReservationsService.reservationsInDepartment(id));

        model.addAttribute("departmentCode",
                departmentReservationsService.getDepartmentCode(id));

        return "department/reservations";
    }

}
