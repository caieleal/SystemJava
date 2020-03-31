package com.example.system.error;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class MyErrorView implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {

        ModelAndView mav = new ModelAndView("error");
        mav.addObject("status", status.value());
        switch (status.value()){
            case 404:
                mav.addObject("error", "Página não existe.");
                mav.addObject("message","A URL para a página '" + model.get("path") + "' não existe.");
                break;
            case 500:
                mav.addObject("error", "Ocorreu um erro.");
                mav.addObject("message", "Erro inexperado, tente novamente.");
                break;
            default:
                mav.addObject("error", model.get("error"));
                mav.addObject("message", model.get("message"));
                break;
        }
        return mav;
    }
}
