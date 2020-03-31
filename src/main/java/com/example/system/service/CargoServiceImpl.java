package com.example.system.service;

import com.example.system.dao.CargoDao;
import com.example.system.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoDao cargoDao;

    @Override
    public void salvar(Cargo cargo) {

        cargoDao.save(cargo);
    }

    @Override
    public void editar(Cargo cargo) {

        cargoDao.update(cargo);
    }

    @Override
    public void excluir(Long id) {

        cargoDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo buscarPorId(Long id) {
       return cargoDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> buscarTodos() {

        return cargoDao.findAll();

    }

    @Override
    public boolean cargoTemFuncionario(Long id) {
        if(buscarPorId(id).getFuncionarios().isEmpty()){
            return false;
        }
        return true;
    }
}
