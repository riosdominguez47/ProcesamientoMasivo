package com.eglobal.transactions.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eglobal.transactions.Entity.Transacciones;
@Repository
public interface TransaccionRepository extends JpaRepository<Transacciones, Long> {

}
