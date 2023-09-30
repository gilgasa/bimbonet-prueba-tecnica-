package com.bimbonet.pruebatecnicagilapi.util;

import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import com.bimbonet.pruebatecnicagilapi.model.entity.PosicionProducto;
import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import com.bimbonet.pruebatecnicagilapi.repository.MaquinaRepository;
import com.bimbonet.pruebatecnicagilapi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Componente que carga datos iniciales en la aplicación al iniciar. Este
 * componente se encarga de la inserción de datos iniciales para productos y
 * máquinas para el arranque inicial del sistema.
 *
 * @author Gilberto García
 * @version 1.0
 * @since 2023-09-03
 */
@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Cargando datos iniciales...");
        // Datos iniciales para productos
        Producto hotNuts = new Producto("108176", "Hot-Nuts 30grs", 17.00, "7501026000119");
        Producto donasAzucar = new Producto("108562", "Donas azúcar 120 grs", 22.00, "7501048100163");
        Producto panqueNuez = new Producto("109182", "Panque nuez 255 grs", 45.00, "7501032923990");
        Producto gansitoEspecial = new Producto("109406", "Gansito edición especial 45grs", 13.00, "7501111103183");

        // Guardamos los productos en la base de datos
        List<Producto> productos = Arrays.asList(hotNuts, donasAzucar, panqueNuez, gansitoEspecial);
        productoRepository.saveAll(productos);

        // Datos iniciales para máquina MTX0001
        Maquina maquinaMTX0001 = new Maquina("MTX0001", "Lote123");

        // Asociamos productos con máquina
        PosicionProducto pos1 = new PosicionProducto("0,0", 11, hotNuts, maquinaMTX0001);
        PosicionProducto pos2 = new PosicionProducto("0,1", 0, donasAzucar, maquinaMTX0001);
        PosicionProducto pos3 = new PosicionProducto("1,0", 1, panqueNuez, maquinaMTX0001);
        PosicionProducto pos4 = new PosicionProducto("1,1", 4, gansitoEspecial, maquinaMTX0001);

        maquinaMTX0001.setPosiciones(Arrays.asList(pos1, pos2, pos3, pos4));

        // Guardamos la máquina en la base de datos
        maquinaRepository.save(maquinaMTX0001);
        LOGGER.info("Datos iniciales cargados exitosamente");
    }
}
