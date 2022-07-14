package uz.lazizbek.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lazizbek.company.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
