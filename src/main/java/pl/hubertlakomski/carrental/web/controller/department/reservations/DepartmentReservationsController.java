package pl.hubertlakomski.carrental.web.controller.department.reservations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.department.reservations.DepartmentReservationsService;

@Controller
@RequestMapping("/department/reservations")
@RequiredArgsConstructor
public class DepartmentReservationsController {

    private final DepartmentReservationsService departmentReservationsService;

    @GetMapping
    public String prepareDepartmentReservations(@RequestParam Long id, Model model){

        model.addAttribute("data",
                departmentReservationsService.reservationsInDepartment(id));

        model.addAttribute("departmentCode",
                departmentReservationsService.getDepartmentCode(id));

        return "department/reservations";
    }

}
