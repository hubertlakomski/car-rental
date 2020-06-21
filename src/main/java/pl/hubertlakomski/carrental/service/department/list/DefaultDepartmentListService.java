package pl.hubertlakomski.carrental.service.department.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultDepartmentListService implements DepartmentListService {

    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public List<DepartmentListData> getDepartments() {

        List<DepartmentListData> departments = new ArrayList<>();

        for(Department department: departmentRepository.findAll()){

            DepartmentListData departmentListData = new DepartmentListData();

            departmentListData.setId(department.getId());
            departmentListData.setCode(department.getCode());

            departmentListData.setCountry(department.getAddress().getCountry());
            departmentListData.setCity(department.getAddress().getCity());
            departmentListData.setAddressLine(department.getAddress().getAddressLine());
            departmentListData.setZipCode(department.getAddress().getZipCode());

            departments.add(departmentListData);
        }

        return departments;
    }
}
