package com.example.system.controller;

import com.example.system.domain.Cargo;
import com.example.system.domain.Funcionario;
import com.example.system.domain.UF;
import com.example.system.service.CargoService;
import com.example.system.service.FuncionarioService;
import com.example.system.validator.FuncionarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private CargoService cargoService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new FuncionarioValidator());
    }

    @GetMapping("/cadastrar")
    public String cadastro(Funcionario funcionario) {
        return "funcionario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelMap) {
        modelMap.addAttribute("funcionarios", funcionarioService.buscarTodos());
        return "funcionario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "funcionario/cadastro";
        }
        funcionarioService.salvar(funcionario);
        attributes.addFlashAttribute("success", "Funcionário inserido com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("funcionario", funcionarioService.buscarPorId(id));
        return "funcionario/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "funcionario/cadastro";
        }
        funcionarioService.editar(funcionario);
        attributes.addFlashAttribute("success", "Funcionário atualizado com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluri(@PathVariable("id") Long id, RedirectAttributes attributes) {
        funcionarioService.excluir(id);
        attributes.addFlashAttribute("success", "Funcionário removido com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    @GetMapping("/buscar/nome")
    public String getPorNome(@RequestParam("nome") String nome, ModelMap modelMap) {
        modelMap.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
        return "funcionario/lista";
    }

    @GetMapping("/buscar/cargo")
    public String getPorCargo(@RequestParam("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("funcionarios", funcionarioService.buscarPorCargo(id));
        return "funcionario/lista";
    }

    @GetMapping("/buscar/data")
    public String getPorDatas(@RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
                              @RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida, ModelMap modelMap) {

        modelMap.addAttribute("funcionarios", funcionarioService.buscarPorData(entrada, saida));
        return "funcionario/lista";
    }

    @ModelAttribute("cargos")
    public List<Cargo> getCargos() {
        return cargoService.buscarTodos();
    }

    @ModelAttribute("ufs")
    public UF[] getUFS() {
        return UF.values();
    }
}
