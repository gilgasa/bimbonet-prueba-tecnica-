package com.bimbonet.pruebatecnicagilapi.service;

import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import java.util.List;
import java.util.Optional;

public interface MaquinaService {

    Maquina guardarMaquina(Maquina maquina);

    List<Maquina> obtenerTodasMaquinas();

    Optional<Maquina> obtenerMaquinaPorId(Long id);

    void eliminarMaquina(Long id);

    Double calcularValorMonetario(Maquina maquina);

    List<Maquina> obtenerMaquinasPorLote(String lote);

    void actualizarStockProducto(Long maquinaId, Long productoId, int nuevoStock);

    List<Maquina> obtenerMaquinasPorValorMonetario();
    
}
