package com.bimbonet.pruebatecnicagilapi.util;

import org.springframework.stereotype.Component;
import com.bimbonet.pruebatecnicagilapi.model.entity.*;
import com.bimbonet.pruebatecnicagilapi.model.dto.*;
import com.bimbonet.pruebatecnicagilapi.model.request.*;
import com.bimbonet.pruebatecnicagilapi.repository.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación concreta de la interfaz Mapper. Esta clase se encarga de las
 * operaciones de mapeo entre entidades y DTOs, así como de solicitudes
 * (requests) y entidades.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
@Component
public class MapperImpl implements Mapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperImpl.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ProductoDTO toProductoDTO(Producto producto) {
        LOGGER.debug("Convirtiendo Producto a ProductoDTO: {}", producto.getNombre());
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setSKU(producto.getSKU());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setCodigoBarras(producto.getCodigoBarras());
        return dto;
    }

    @Override
    public Producto fromProductoRequest(ProductoRequest productoRequest) {
        LOGGER.debug("Convirtiendo ProductoRequest a Producto. Nombre: {}", productoRequest.getNombre());
        Producto producto = new Producto();
        producto.setSKU(productoRequest.getSKU());
        producto.setNombre(productoRequest.getNombre());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setCodigoBarras(productoRequest.getCodigoBarras());
        return producto;
    }

    @Override
    public MaquinaDTO toMaquinaDTO(Maquina maquina) {
        LOGGER.debug("Convirtiendo Maquina a MaquinaDTO. Número de Máquina: {}", maquina.getNumeroMaquina());
        List<PosicionProductoDTO> posicionesDTO = new ArrayList<>();
        if (maquina.getProductos() != null) {
            posicionesDTO = maquina.getProductos().stream()
                    .map(this::toPosicionProductoDTO)
                    .collect(Collectors.toList());
        }

        return new MaquinaDTO(
                maquina.getId(),
                maquina.getNumeroMaquina(),
                maquina.getLote(),
                posicionesDTO
        );
    }

    @Override
    public PosicionProductoDTO toPosicionProductoDTO(PosicionProducto posicionProducto) {
        LOGGER.debug("Convirtiendo PosicionProducto a PosicionProductoDTO para el Producto: {}", posicionProducto.getProducto().getNombre());
        return new PosicionProductoDTO(
                posicionProducto.getId(),
                posicionProducto.getPosicion(),
                posicionProducto.getUnidadesDisponibles(),
                toProductoDTO(posicionProducto.getProducto())
        );
    }

    @Override
    public Producto fromProductoDTO(ProductoDTO dto) {
        LOGGER.debug("Convirtiendo ProductoDTO a Producto: {}", dto.getNombre());
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setSKU(dto.getSKU());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCodigoBarras(dto.getCodigoBarras());
        return producto;
    }

    @Override
    public Maquina fromMaquinaDTO(MaquinaDTO dto) {
        LOGGER.debug("Convirtiendo MaquinaDTO a Maquina: {}", dto.getNumeroMaquina());
        Maquina maquina = new Maquina();
        maquina.setId(dto.getId());
        maquina.setNumeroMaquina(dto.getNumeroMaquina());
        maquina.setLote(dto.getLote());
        // Nota: Necesitarás un método para convertir la lista de PosicionProductoDTO a PosicionProducto
        maquina.setProductos(dto.getProductos().stream()
                .map(this::fromPosicionProductoDTO)
                .collect(Collectors.toList()));
        return maquina;
    }

    @Override
    public PosicionProducto fromPosicionProductoDTO(PosicionProductoDTO dto) {
        LOGGER.debug("Convirtiendo PosicionProductoDTO a PosicionProducto con posición: {}", dto.getPosicion());
        PosicionProducto posicionProducto = new PosicionProducto();
        posicionProducto.setId(dto.getId());
        posicionProducto.setPosicion(dto.getPosicion());
        posicionProducto.setUnidadesDisponibles(dto.getUnidadesDisponibles());
        posicionProducto.setProducto(fromProductoDTO(dto.getProducto()));
        return posicionProducto;
    }

    @Override
    public Maquina fromMaquinaRequest(MaquinaRequest maquinaRequest) {
        LOGGER.debug("Convirtiendo MaquinaRequest a Maquina. Número de Máquina: {}", maquinaRequest.getNumeroMaquina());
        Maquina maquina = new Maquina();
        maquina.setNumeroMaquina(maquinaRequest.getNumeroMaquina());
        maquina.setLote(maquinaRequest.getLote());

        // Convertir la lista de PosicionProductoRequest a una lista de PosicionProducto
        if (maquinaRequest.getProductos() != null && !maquinaRequest.getProductos().isEmpty()) {
            List<PosicionProducto> posiciones = maquinaRequest.getProductos().stream()
                    .map(this::fromPosicionProductoRequest) // Asume que tienes un método que convierte de PosicionProductoRequest a PosicionProducto
                    .collect(Collectors.toList());
            maquina.setProductos(posiciones);
        }

        return maquina;
    }

    public PosicionProducto fromPosicionProductoRequest(PosicionProductoRequest request) {
        LOGGER.debug("Convirtiendo PosicionProductoRequest a PosicionProducto con posición: {} y ProductoID: {}", request.getPosicion(), request.getProductoId());
        PosicionProducto posicionProducto = new PosicionProducto();

        posicionProducto.setPosicion(request.getPosicion());
        posicionProducto.setUnidadesDisponibles(request.getUnidadesDisponibles());

        // Buscar el Producto en la base de datos utilizando el productoId
        Producto producto = productoRepository.findById(request.getProductoId()).orElse(null);

        if (producto != null) {
            posicionProducto.setProducto(producto);
        } else {
            // Lanza una excepción o maneja este caso donde el producto no se encuentra.
            throw new RuntimeException("Producto no encontrado con ID: " + request.getProductoId());
        }

        return posicionProducto;
    }

}
