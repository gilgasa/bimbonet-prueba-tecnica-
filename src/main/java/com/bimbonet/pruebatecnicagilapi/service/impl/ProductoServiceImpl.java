package com.bimbonet.pruebatecnicagilapi.service.impl;

import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import com.bimbonet.pruebatecnicagilapi.repository.ProductoRepository;
import com.bimbonet.pruebatecnicagilapi.service.ProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación del servicio para la gestión de productos.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> obtenerTodosProductos() {
        LOGGER.info("Obteniendo lista de todos los productos...");
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        LOGGER.info("Buscando producto con ID: {}", id);
        return productoRepository.findById(id);
    }

    @Override
    public void eliminarProducto(Long id) {
        LOGGER.info("Eliminando producto con ID: {}", id);
        productoRepository.deleteById(id);
    }
}
