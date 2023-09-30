package com.bimbonet.pruebatecnicagilapi.repository;

import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import com.bimbonet.pruebatecnicagilapi.model.entity.PosicionProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosicionProductoRepository extends JpaRepository<PosicionProducto, Long> {

    List<PosicionProducto> findByMaquina(Maquina maquina);
}
