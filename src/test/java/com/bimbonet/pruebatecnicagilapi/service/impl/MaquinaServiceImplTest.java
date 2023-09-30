package com.bimbonet.pruebatecnicagilapi.service.impl;

import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import com.bimbonet.pruebatecnicagilapi.repository.MaquinaRepository;
import com.bimbonet.pruebatecnicagilapi.repository.PosicionProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MaquinaServiceImplTest {

    @InjectMocks
    private MaquinaServiceImpl maquinaService;

    @Mock
    private MaquinaRepository maquinaRepository;

    @Mock
    private PosicionProductoRepository posicionProductoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarMaquina() {
        Maquina maquina = new Maquina();
        when(maquinaRepository.save(maquina)).thenReturn(maquina);
        assertEquals(maquina, maquinaService.guardarMaquina(maquina));
    }

    @Test
    void testObtenerMaquinaPorId() {
        Maquina maquina = new Maquina();
        when(maquinaRepository.findById(1L)).thenReturn(Optional.of(maquina));
        assertEquals(Optional.of(maquina), maquinaService.obtenerMaquinaPorId(1L));
    }

}
