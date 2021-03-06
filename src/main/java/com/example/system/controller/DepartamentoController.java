package com.example.system.controller;

import com.example.system.domain.Departamento;
import com.example.system.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastro(Departamento departamento) {
        return "departamento/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("departamentos", departamentoService.buscarTodos());
        return "departamento/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "departamento/cadastro";
        }
        departamentoService.salvar(departamento);
        attributes.addFlashAttribute("success", "Departamento criado com sucesso.");

        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap modelMap) {

        modelMap.addAttribute("departamento", departamentoService.buscarPorId(id));
        return "departamento/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attributes) {

        if(result.hasErrors()){
            return "departamento/cadastro";
        }
        departamentoService.editar(departamento);
        attributes.addFlashAttribute("success", "Departamento editado com sucesso.");

        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap modelMap) {

        if (departamentoService.departamentoTemCargo(id)) {
            modelMap.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculados(s).");
        } else {
            departamentoService.excluir(id);
            modelMap.addAttribute("success", "Departamento excluido com sucesso.");
        }
        return listar(modelMap);
    }
}
