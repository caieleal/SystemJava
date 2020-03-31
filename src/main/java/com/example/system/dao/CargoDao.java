package com.example.system.dao;

import com.example.system.domain.Cargo;

import java.util.List;

public interface CargoDao {
    public void save(Cargo cargo);

    public void update(Cargo cargo);

    public void delete(Long id);

    public Cargo findById(Long id);

    public List<Cargo> findAll();
}
