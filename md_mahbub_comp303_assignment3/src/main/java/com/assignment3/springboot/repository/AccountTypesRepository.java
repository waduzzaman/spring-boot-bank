package com.assignment3.springboot.repository;

import com.assignment3.springboot.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypesRepository extends JpaRepository<AccountType, Integer>{
}
