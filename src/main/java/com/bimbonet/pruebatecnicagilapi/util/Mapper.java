package com.bimbonet.pruebatecnicagilapi.util;

import com.bimbonet.pruebatecnicagilapi.model.entity.*;
import com.bimbonet.pruebatecnicagilapi.model.dto.*;
import com.bimbonet.pruebatecnicagilapi.model.request.*;

/**
 * Interface que define métodos para el mapeo entre entidades y DTOs, y
 * viceversa. Se encarga de la conversión entre los diferentes tipos de objetos
 * dentro de la aplicación.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
public interface Mapper {

    ProductoDTO toProductoDTO(Producto producto);

    Producto fromProductoDTO(ProductoDTO dto);

    Producto fromProductoRequest(ProductoRequest productoRequest);

    MaquinaDTO toMaquinaDTO(Maquina maquina);

    Maquina fromMaquinaDTO(MaquinaDTO dto);

    Maquina fromMaquinaRequest(MaquinaRequest maquinaRequest);

    PosicionProductoDTO toPosicionProductoDTO(PosicionProducto posicionProducto);

    PosicionProducto fromPosicionProductoDTO(PosicionProductoDTO dto);
}
