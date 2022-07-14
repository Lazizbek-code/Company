package uz.lazizbek.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lazizbek.company.entity.Worker;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    List<Worker> findAllByDepartmentId(Integer department_id);
}
