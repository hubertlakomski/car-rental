package pl.hubertlakomski.carrental.service.department.add;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.repository.AddressRepository;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class DefaultDepartmentAddService implements DepartmentAddService{

    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;

    @Override
    public void save(DepartmentAddData departmentAddData) {

        Department department = new Department();

        department.setCode(departmentAddData.getCode());

        Address address = new Address();

        address.setCountry(departmentAddData.getCountry());
        address.setCity(departmentAddData.getCity());
        address.setAddressLine(departmentAddData.getAddressLine());
        address.setZipCode(departmentAddData.getZipCode());

        addressRepository.save(address);

        department.setAddress(address);

        departmentRepository.save(department);

    }


}
