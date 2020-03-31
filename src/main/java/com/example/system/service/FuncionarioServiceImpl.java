package com.example.system.service;

import com.example.system.dao.FuncionarioDao;
import com.example.system.domain.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioDao funcionarioDao;

    @Override
    @Transactional(readOnly = false)
    public void salvar(Funcionario funcionario) {

        funcionarioDao.save(funcionario);
    }

    @Transactional(readOnly = false)
    @Override
    public void editar(Funcionario funcionario) {

        funcionarioDao.update(funcionario);
    }

    @Transactional(readOnly = false)
    @Override
    public void excluir(Long id) {

        funcionarioDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Funcionario buscarPorId(Long id) {
       return funcionarioDao.findById(id);

    }

    @Transactional(readOnly = true)
    @Override
    public List<Funcionario> buscarTodos() {
       return funcionarioDao.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioDao.findByNome(nome);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarPorCargo(Long id) {
        return funcionarioDao.findByCargoId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Funcionario> buscarPorData(LocalDate entrada, LocalDate saida) {
        if(entrada != null && saida != null){
            return funcionarioDao.findByDataEntradaDataSaida(entrada, saida);
        } else if(entrada != null){
            return funcionarioDao.findByDataEntrada(entrada);
        }else if(saida != null){
            return funcionarioDao.findByDataSaida(saida);
        }else {
            return new ArrayList<>();
        }
    }

}
