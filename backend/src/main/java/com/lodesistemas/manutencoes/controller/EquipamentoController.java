package com.lodesistemas.manutencoes.controller;

import com.lodesistemas.manutencoes.model.Equipamento;
import com.lodesistemas.manutencoes.model.Status;
import com.lodesistemas.manutencoes.service.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
@CrossOrigin(origins = "http://localhost:5173")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Equipamento> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Equipamento find(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipamento create(@Valid @RequestBody Equipamento equipamento) {
        return service.create(equipamento);
    }

    @PutMapping("/{id}")
    public Equipamento update(@PathVariable Long id, @Valid @RequestBody Equipamento equipamento) {
        return service.update(id, equipamento);
    }

    @PatchMapping("/{id}/status")
    public Equipamento updateStatus(@PathVariable Long id, @RequestParam Status status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}