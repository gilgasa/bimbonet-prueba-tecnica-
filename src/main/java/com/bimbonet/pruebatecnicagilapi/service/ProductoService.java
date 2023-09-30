package com.bimbonet.pruebatecnicagilapi.service;

import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto guardarProducto(Producto producto);

    List<Producto> obtenerTodosProductos();

    Optional<Producto> obtenerProductoPorId(Long id);

    void eliminarProducto(Long id);
}
