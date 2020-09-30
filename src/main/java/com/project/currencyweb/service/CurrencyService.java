package com.project.currencyweb.service;

import com.project.currencyweb.models.CcyAmt;
import com.project.currencyweb.models.CurrencyConversion;
import com.project.currencyweb.repository.CurrencyRepository;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {


    private CurrencyRepository currencyRepository;

    public CurrencyService (CurrencyRepository currencyRepository){
    this.currencyRepository= currencyRepository;
    }

    public List<CcyAmt> getCurrencies(){
        List<CcyAmt> currencyList = this.currencyRepository.findAll();
        currencyList.sort(Comparator.comparing(CcyAmt::getCcy));
        return currencyList;
    }

    public Optional<Double> conversion(CurrencyConversion currencyConversion){
            Optional<CcyAmt> toOptional = this.currencyRepository.findById(currencyConversion.getTo().toUpperCase());
            Optional<CcyAmt> fromOptional = this.currencyRepository.findById(currencyConversion.getFrom().toUpperCase());

            if(toOptional.isPresent() && fromOptional.isPresent()) {

                if (currencyConversion.getValue() < 0) {
                    return Optional.empty();
                }

                CcyAmt to = toOptional.get();
                CcyAmt from = fromOptional.get();
                Double toValue = to.getAmt();
                Double fromValue = from.getAmt();

                Double result = toValue * currencyConversion.getValue() / fromValue;



                return Optional.of( DoubleRounder.round(result, 4));
            }
            return Optional.empty();
            }
    }




