package com.bimbonet.pruebatecnicagilapi.repository;

import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
