package com.bimbonet.pruebatecnicagilapi.repository;

import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void testSaveAndFindById() {
        // Inserta un producto
        Producto producto = new Producto();
        producto = productoRepository.save(producto);

        // Verifica que puedes encontrarlo por ID
        assertTrue(productoRepository.findById(producto.getId()).isPresent());
    }
}
