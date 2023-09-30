package com.bimbonet.pruebatecnicagilapi.repository;

import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {

    List<Maquina> findByLote(String lote);
}
