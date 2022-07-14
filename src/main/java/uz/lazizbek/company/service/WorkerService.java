package uz.lazizbek.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.company.entity.Address;
import uz.lazizbek.company.entity.Department;
import uz.lazizbek.company.entity.Worker;
import uz.lazizbek.company.payload.ApiResponse;
import uz.lazizbek.company.payload.WorkerDto;
import uz.lazizbek.company.repository.AddressRepository;
import uz.lazizbek.company.repository.DepartmentRepository;
import uz.lazizbek.company.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getWorkers()
    {
        return workerRepository.findAll();
    }

    public Worker getWorker(Integer id)
    {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        return workerOptional.orElse(null);
    }

    public List<Worker> getWorkersByDepartmentId(Integer departmentId)
    {
        return workerRepository.findAllByDepartmentId(departmentId);
    }

    public ApiResponse addWorker(WorkerDto workerDto)
    {
        Optional<Department> departmentOptional = departmentRepository.findById(workerDto.getDepartmentId());
        if (!departmentOptional.isPresent())
            return new ApiResponse("Departament mavjud emas", false);

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(departmentOptional.get());
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);
        worker.setAddress(saveAddress);
        workerRepository.save(worker);
        return  new ApiResponse("Ishchi saqlandi", true);
    }

    public ApiResponse editWorker(Integer id, WorkerDto workerDto)
    {
        Optional<Worker> workerOptional = workerRepository.findById(id);
        if (!workerOptional.isPresent())
            return new ApiResponse("Ishchi mavjud emas", false);
        Worker worker = workerOptional.get();
        Optional<Address> addressOptional = addressRepository.findById(worker.getAddress().getId());
        if (!addressOptional.isPresent())
            return new ApiResponse("Address mavjud emas", false);
        Optional<Department> departmentOptional = departmentRepository.findById(worker.getDepartment().getId());
        if (!departmentOptional.isPresent())
            return new ApiResponse("Departament mayjud emas", false);
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(addressOptional.get());
        worker.setDepartment(departmentOptional.get());
        workerRepository.save(worker);
        return new ApiResponse("Ishchi tahrirlandi", true);
    }


}
