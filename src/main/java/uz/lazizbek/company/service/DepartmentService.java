package uz.lazizbek.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.company.entity.Company;
import uz.lazizbek.company.entity.Department;
import uz.lazizbek.company.payload.ApiResponse;
import uz.lazizbek.company.payload.DepartmentDto;
import uz.lazizbek.company.repository.CompanyRepository;
import uz.lazizbek.company.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartments()
    {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Integer id)
    {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        return departmentOptional.orElse(null);
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto)
    {
        boolean exists = departmentRepository.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId());
        if (exists)
            return new ApiResponse("Bu companiyada bunday departament mavjud", false);
        Department department = new Department();
        department.setName(departmentDto.getName());
        Optional<Company> companyOptional = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyOptional.isPresent())
            return new  ApiResponse("Companiya mavjud emas", false);
        Company company = companyOptional.get();
        department.setCompany(company);
        departmentRepository.save(department);
        return new ApiResponse("Departament saqlandi", true);
    }

    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto)
    {
        boolean exists = departmentRepository.existsByNameAndCompanyIdAndIdNot(departmentDto.getName(), departmentDto.getCompanyId(), id);
        if (exists)
            return new ApiResponse("Bu companiyada bunday nomdagi departament mavjud", false);
        Optional<Company> companyOptional = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyOptional.isPresent())
            return new  ApiResponse("Companiya mavjud emas", false);
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(!departmentOptional.isPresent())
            return new ApiResponse("Departament mavjud emas", false);
        Department department = departmentOptional.get();
        department.setName(departmentDto.getName());
        department.setCompany(companyOptional.get());
        departmentRepository.save(department);
        return new ApiResponse("Departament tahrirlandi", true);
    }
}
