package com.bimbonet.pruebatecnicagilapi.service.impl;

import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import com.bimbonet.pruebatecnicagilapi.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceImplTest {

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarProducto() {
        Producto producto = new Producto();
        when(productoRepository.save(producto)).thenReturn(producto);
        assertEquals(producto, productoService.guardarProducto(producto));
    }

    @Test
    void testObtenerProductoPorId() {
        Producto producto = new Producto();
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        assertEquals(Optional.of(producto), productoService.obtenerProductoPorId(1L));
    }
}
