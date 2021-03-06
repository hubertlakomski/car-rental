package pl.hubertlakomski.carrental.web.controller.department.returns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hubertlakomski.carrental.service.department.returns.DepartmentReturnsService;

@Controller
@RequestMapping("/department/returns")
@RequiredArgsConstructor
public class DepartmentReturnsController {

    private final DepartmentReturnsService departmentReturnsService;

    @GetMapping
    public String prepareDepartmentReservations(@RequestParam Long id, Model model){

        model.addAttribute("data",
                departmentReturnsService.returnsInDepartment(id));

        model.addAttribute("departmentCode",
                departmentReturnsService.getDepartmentCode(id));

        return "department/returns";
    }
}
