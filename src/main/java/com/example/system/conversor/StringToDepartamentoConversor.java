package com.example.system.conversor;

import com.example.system.domain.Departamento;
import com.example.system.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConversor implements Converter<String, Departamento> {

    @Autowired
    private DepartamentoService departamentoService;

    @Override
    public Departamento convert(String text) {
        if(text.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(text);
        return departamentoService.buscarPorId(id);
    }
}
