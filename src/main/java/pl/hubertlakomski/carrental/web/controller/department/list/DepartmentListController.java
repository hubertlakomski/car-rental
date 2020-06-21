package pl.hubertlakomski.carrental.web.controller.department.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.service.department.add.DepartmentAddData;
import pl.hubertlakomski.carrental.service.department.add.DepartmentAddService;
import pl.hubertlakomski.carrental.service.department.edit.DepartmentEditData;
import pl.hubertlakomski.carrental.service.department.edit.DepartmentEditService;
import pl.hubertlakomski.carrental.service.department.list.DepartmentListService;

import javax.validation.Valid;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentListController {

    private final DepartmentListService departmentListService;
    private final DepartmentAddService departmentAddService;
    private final DepartmentEditService departmentEditService;

    @GetMapping
    public String prepareListPage(Model model){

        model.addAttribute("departmentDataList", departmentListService.getDepartments());

        return "department/list/page";
    }

    @PostMapping(params={"edit", "id"})
    public String editDepartment(@Valid DepartmentEditData departmentEditData,
                                 BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "department/list/page";
        }

        departmentEditService.edit(departmentEditData);

        return "redirect:/departments";
    }

    @PostMapping(params={"add"})
    public String addClientDetails(@Valid DepartmentAddData departmentAddData,
                                   BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "department/list/page";
        }

        departmentAddService.save(departmentAddData);


        return "redirect:/departments";
    }
}
