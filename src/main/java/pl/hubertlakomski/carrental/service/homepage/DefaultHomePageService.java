package pl.hubertlakomski.carrental.service.homepage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service @Slf4j
public class DefaultHomePageService implements HomePageService {

    private final DepartmentRepository departmentRepository;

    public DefaultHomePageService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    @Override
    public List<HomePageData> data() {

        List<HomePageData> data = new ArrayList<>();

        for(Department department: departmentRepository.findAll()){

            HomePageData homePageData = new HomePageData();

            homePageData.setDepartmentCode(department.getCode());
            homePageData.setDepartmentId(department.getId());

            data.add(homePageData);
        }

        return data;
    }
}
