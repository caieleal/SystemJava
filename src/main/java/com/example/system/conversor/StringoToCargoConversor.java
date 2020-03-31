package com.example.system.conversor;

import com.example.system.domain.Cargo;
import com.example.system.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringoToCargoConversor implements Converter<String, Cargo> {

    @Autowired
    private CargoService cargoService;

    @Override
    public Cargo convert(String text) {
        if(text.isEmpty()){
            return null;
        }
        Long id = Long.valueOf(text);
        return cargoService.buscarPorId(id);
    }
}
