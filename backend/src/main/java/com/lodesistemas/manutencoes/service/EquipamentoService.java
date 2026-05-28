package com.lodesistemas.manutencoes.service;

import com.lodesistemas.manutencoes.model.Equipamento;
import com.lodesistemas.manutencoes.model.Status;
import com.lodesistemas.manutencoes.repository.EquipamentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipamentoService {

    private final EquipamentoRepository repository;

    public EquipamentoService(EquipamentoRepository repository) {
        this.repository = repository;
    }

    public List<Equipamento> listAll() {
        return repository.findAll();
    }

    public Equipamento findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipamento não encontrado"));
    }

    public Equipamento create(Equipamento equipamento) {
        return repository.save(equipamento);
    }

    public Equipamento update(Long id, Equipamento dados) {
        Equipamento existente = findById(id);
        existente.setNome(dados.getNome());
        existente.setTipo(dados.getTipo());
        existente.setDataInstalacao(dados.getDataInstalacao());
        existente.setStatus(dados.getStatus());
        return repository.save(existente);
    }

    public Equipamento updateStatus(Long id, Status novoStatus) {
        Equipamento equipamento = findById(id);
        equipamento.setStatus(novoStatus);
        return repository.save(equipamento);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}