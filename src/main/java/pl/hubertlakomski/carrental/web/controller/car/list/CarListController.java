package pl.hubertlakomski.carrental.web.controller.car.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hubertlakomski.carrental.service.car.add.CarAddData;
import pl.hubertlakomski.carrental.service.car.add.CarAddService;
import pl.hubertlakomski.carrental.service.car.edit.CarEditData;
import pl.hubertlakomski.carrental.service.car.edit.CarEditService;
import pl.hubertlakomski.carrental.service.car.list.CarListService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarListController {

    private final CarListService carListService;
    private final CarAddService carAddService;
    private final CarEditService carEditService;

    @GetMapping
    public String prepareCarListPage(Model model){

        model.addAttribute("dataList", carListService.getCars());
        model.addAttribute("departments", carListService.getDepartments());
        model.addAttribute("sippCodes", carListService.getSippCodes());
        model.addAttribute("statuses", carListService.getStatuses());

        return "car/list/page";
    }

    @PostMapping(params={"edit", "id"})
    public String editCarDetails(@Valid CarEditData carEditData,
                                    BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "car/list/page";
        }

        carEditService.editCar(carEditData);


        return "redirect:/cars";
    }

    @PostMapping(params={"add"})
    public String editCarDetails(@Valid CarAddData carAddData,
                                    BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            return "car/list/page";
        }

        carAddService.addCar(carAddData);


        return "redirect:/cars";
    }

}
