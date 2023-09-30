package com.bimbonet.pruebatecnicagilapi.service.impl;

import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import com.bimbonet.pruebatecnicagilapi.model.entity.PosicionProducto;
import com.bimbonet.pruebatecnicagilapi.model.exception.NotFoundException;
import com.bimbonet.pruebatecnicagilapi.repository.MaquinaRepository;
import com.bimbonet.pruebatecnicagilapi.repository.PosicionProductoRepository;
import com.bimbonet.pruebatecnicagilapi.service.MaquinaService;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación del servicio para la gestión de máquinas.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
@Service
public class MaquinaServiceImpl implements MaquinaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaquinaServiceImpl.class);

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private PosicionProductoRepository posicionProductoRepository;

    @Override
    public Maquina guardarMaquina(Maquina maquina) {
        LOGGER.info("Guardando máquina con ID: {}", maquina.getId());
        return maquinaRepository.save(maquina);
    }

    @Override
    public List<Maquina> obtenerTodasMaquinas() {
        LOGGER.info("Obteniendo lista de todas las máquinas...");
        return maquinaRepository.findAll();
    }

    @Override
    public Optional<Maquina> obtenerMaquinaPorId(Long id) {
        LOGGER.info("Buscando máquina con ID: {}", id);
        return maquinaRepository.findById(id);
    }

    @Override
    public void eliminarMaquina(Long id) {
        LOGGER.info("Eliminando máquina con ID: {}", id);
        maquinaRepository.deleteById(id);
    }

    @Override
    public Double calcularValorMonetario(Maquina maquina) {
        LOGGER.info("Calculando valor monetario para la máquina con ID: {}", maquina.getId());
        List<PosicionProducto> posiciones = posicionProductoRepository.findByMaquina(maquina);
        return posiciones.stream()
                .mapToDouble(p -> p.getProducto().getPrecio() * p.getUnidadesDisponibles())
                .sum();
    }

    @Override
    public List<Maquina> obtenerMaquinasPorLote(String lote) {
        LOGGER.info("Obteniendo máquinas por lote: {}", lote);
        return maquinaRepository.findByLote(lote);
    }

    @Override
    public void actualizarStockProducto(Long maquinaId, Long productoId, int nuevoStock) {
        LOGGER.info("Actualizando stock del producto con ID: {} en la máquina con ID: {}. Nuevo stock: {}", productoId, maquinaId, nuevoStock);
        Maquina maquina = maquinaRepository.findById(maquinaId).orElseThrow(() -> new NotFoundException("Máquina no encontrada"));
        PosicionProducto posicionProducto = maquina.getProductos().stream()
                .filter(pp -> pp.getProducto().getId().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Producto no encontrado en la máquina"));
        posicionProducto.setUnidadesDisponibles(nuevoStock);
        posicionProductoRepository.save(posicionProducto);
    }

    @Override
    public List<Maquina> obtenerMaquinasPorValorMonetario() {
        LOGGER.info("Obteniendo lista de máquinas ordenadas por valor monetario...");
        return maquinaRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(this::calcularValorMonetario).reversed())
                .collect(Collectors.toList());
    }

}
