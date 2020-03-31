package com.example.system.dao;

import com.example.system.domain.Departamento;

import java.util.List;

public interface DepartamentoDao {
    public void save(Departamento departamento);

    public void update(Departamento departamento);

    public void delete(Long id);

    public Departamento findById(Long id);

    public List<Departamento> findAll();
}
