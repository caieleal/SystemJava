package com.example.system.service;

import com.example.system.domain.Cargo;
import com.example.system.domain.Funcionario;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioService {

    public void salvar(Funcionario funcionario);

    public void editar(Funcionario funcionario);

    public void excluir(Long id);

    public Funcionario buscarPorId(Long id);

    public List<Funcionario> buscarTodos();

    List<Funcionario> buscarPorNome(String nome);

    List<Funcionario> buscarPorCargo(Long id);

    List<Funcionario> buscarPorData(LocalDate entrada, LocalDate saida);
}
