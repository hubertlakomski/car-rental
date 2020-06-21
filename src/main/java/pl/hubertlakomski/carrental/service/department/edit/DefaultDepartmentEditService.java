package pl.hubertlakomski.carrental.service.department.edit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.repository.AddressRepository;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class DefaultDepartmentEditService implements DepartmentEditService {

    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;

    @Override
    public void edit(DepartmentEditData departmentEditData) {

        Department department = departmentRepository.getById(departmentEditData.getId());

        department.setId(departmentEditData.getId());

        department.setCode(departmentEditData.getCode());

        Address address = new Address();

        address.setCountry(departmentEditData.getCountry());
        address.setCity(departmentEditData.getCity());
        address.setAddressLine(departmentEditData.getAddressLine());
        address.setZipCode(departmentEditData.getZipCode());

        addressRepository.save(address);

        department.setAddress(address);

        departmentRepository.save(department);

    }


}
