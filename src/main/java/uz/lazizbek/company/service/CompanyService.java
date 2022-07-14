package uz.lazizbek.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.company.entity.Address;
import uz.lazizbek.company.entity.Company;
import uz.lazizbek.company.payload.ApiResponse;
import uz.lazizbek.company.payload.CompanyDto;
import uz.lazizbek.company.repository.AddressRepository;
import uz.lazizbek.company.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository  addressRepository;

    public List<Company> getCompanies()
    {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id)
    {
        Optional<Company> companyOptional = companyRepository.findById(id);
        return companyOptional.orElse(null);
    }

    public ApiResponse addCompany(CompanyDto companyDto)
    {
        boolean companyName = companyRepository.existsByCompanyName(companyDto.getCompanyName());
        if (companyName)
            return new ApiResponse("Bunday companiya mavjud", false);
        Company company = new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setDirectorName(companyDto.getDirectorName());
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);
        company.setAddress(saveAddress);
        companyRepository.save(company);
        return new ApiResponse("Companiya saqlandi", true);
    }

    public ApiResponse editCompany(Integer id, CompanyDto companyDto)
    {
        boolean exists = companyRepository.existsByCompanyNameAndIdNot(companyDto.getCompanyName(), id);
        if (exists)
            return new ApiResponse("Bunday nomli companiya mavjud", false);
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent())
            return new ApiResponse("Companiya topilmadi", false);
        Company company = companyOptional.get();
        Optional<Address> addressOptional = addressRepository.findById(company.getAddress().getId());
        if (!addressOptional.isPresent())
            return new ApiResponse("Address topilmadi", false);
        company.setCompanyName(companyDto.getCompanyName());
        company.setDirectorName(companyDto.getDirectorName());
        Address address = addressOptional.get();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);
        company.setAddress(saveAddress);
        companyRepository.save(company);
        return new ApiResponse("Companiya tahrirlandi", true);
    }

}
