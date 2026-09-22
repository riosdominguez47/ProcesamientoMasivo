package com.eglobal.transactions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eglobal.transactions.Entity.ControlLotes;



@Repository
public interface ControlLoteRepository extends JpaRepository<ControlLotes, Long> {
	
}


