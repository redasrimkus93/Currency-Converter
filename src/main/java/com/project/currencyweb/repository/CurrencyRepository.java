package com.project.currencyweb.repository;

import com.project.currencyweb.models.CcyAmt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<CcyAmt, String> {

    @Override
    List<CcyAmt> findAll();
}
