package com.example.system.validator;

import com.example.system.domain.Funcionario;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class FuncionarioValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Funcionario.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Funcionario f = (Funcionario) target;

        LocalDate entrada = f.getDataEntrada();

        if (f.getDataSaida() != null) {
            if(f.getDataSaida().isBefore(entrada)){
                errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
            }
        }
    }
}
