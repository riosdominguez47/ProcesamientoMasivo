package com.eglobal.transactions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eglobal.transactions.Entity.DetalleErrores;
@Repository
public interface DetalleErrorRepository extends JpaRepository<DetalleErrores, Long> {

}
