package uz.lazizbek.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lazizbek.company.entity.Worker;
import uz.lazizbek.company.payload.ApiResponse;
import uz.lazizbek.company.payload.WorkerDto;
import uz.lazizbek.company.service.WorkerService;

import javax.validation.Valid;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping(value = "/api/workers")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> getWorkers()
    {
        List<Worker> workers = workerService.getWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Integer id)
    {
        Worker worker = workerService.getWorker(id);
        return ResponseEntity.ok(worker);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Worker>> getWorkersByDepartmentId(@PathVariable Integer departmentId)
    {
        List<Worker> workers = workerService.getWorkersByDepartmentId(departmentId);
        return ResponseEntity.ok(workers);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto)
    {
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@PathVariable Integer id, @Valid @RequestBody WorkerDto workerDto)
    {
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? 202:409).body(apiResponse);
    }

}
