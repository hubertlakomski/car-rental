package pl.hubertlakomski.carrental.web.controller.employee.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.service.employee.add.EmployeeAddData;
import pl.hubertlakomski.carrental.service.employee.add.EmployeeAddService;
import pl.hubertlakomski.carrental.service.employee.edit.EmployeeEditData;
import pl.hubertlakomski.carrental.service.employee.edit.EmployeeEditService;
import pl.hubertlakomski.carrental.service.employee.list.EmployeeListService;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeListController {

    private final EmployeeListService employeeListService;
    private final EmployeeAddService employeeAddService;
    private final EmployeeEditService employeeEditService;

    @GetMapping
    public String processListPage(Model model){

        model.addAttribute("employeeDataList", employeeListService.getEmployees());
        model.addAttribute("departments", employeeListService.getDepartments());
        model.addAttribute("roles", employeeListService.getRoles());

        return "employees/list/page";
    }

    @PostMapping(params={"add"})
    public String addEmployee(@Valid EmployeeAddData employeeAddData,
                                   BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "employees/list/page";
        }

        employeeAddService.add(employeeAddData);


        return "redirect:/employees";
    }

    @PostMapping(params={"edit", "id"})
    public String editEmployee(@Valid EmployeeEditData employeeEditData,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "employees/list/page";
        }

        employeeEditService.edit(employeeEditData);


        return "redirect:/employees";
    }


}
