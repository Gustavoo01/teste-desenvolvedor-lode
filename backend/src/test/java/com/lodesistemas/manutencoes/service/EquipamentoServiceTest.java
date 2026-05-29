package com.lodesistemas.manutencoes.service;

import com.lodesistemas.manutencoes.model.Equipamento;
import com.lodesistemas.manutencoes.model.Status;
import com.lodesistemas.manutencoes.repository.EquipamentoRepository;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EquipamentoServiceTest {

    private Validator validator;
    private EquipamentoRepository repository;
    private EquipamentoService service;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        repository = mock(EquipamentoRepository.class);
        service = new EquipamentoService(repository);
    }

    @Test
    @DisplayName("Não deve permitir equipamento sem nome")
    void naoDeveCadastrarSemNome() {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome("");
        equipamento.setTipo("Bomba");

        Set<ConstraintViolation<Equipamento>> violacoes = validator.validate(equipamento);

        assertFalse(violacoes.isEmpty());
        assertTrue(violacoes.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("nome")));
    }

    @Test
    @DisplayName("Deve alterar o status corretamente")
    void deveAlterarStatus() {
        Equipamento equipamento = new Equipamento();
        equipamento.setId(1L);
        equipamento.setNome("Bomba");
        equipamento.setStatus(Status.OPERACIONAL);

        when(repository.findById(1L)).thenReturn(Optional.of(equipamento));
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Equipamento resultado = service.updateStatus(1L, Status.CRITICO);

        assertEquals(Status.CRITICO, resultado.getStatus());
        verify(repository).save(equipamento);
    }
}