package com.example.system.dao;

import com.example.system.domain.Funcionario;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioDao {

    public void save(Funcionario funcionario);

    public void update(Funcionario funcionario);

    public void delete(Long id);

    public Funcionario findById(Long id);

    public List<Funcionario> findAll();

    public List<Funcionario> findByNome(String nome);

    public List<Funcionario> findByCargoId(Long id);

    List<Funcionario> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

    List<Funcionario> findByDataEntrada(LocalDate entrada);

    List<Funcionario> findByDataSaida(LocalDate saida);
}
