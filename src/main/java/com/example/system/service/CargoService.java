package com.example.system.service;

import com.example.system.domain.Cargo;

import java.util.List;

public interface CargoService {

    public void salvar(Cargo cargo);
    public void editar(Cargo cargo);
    public void excluir(Long id);
    public Cargo buscarPorId(Long id);
    public List<Cargo> buscarTodos();

    boolean cargoTemFuncionario(Long id);
}
