package com.bimbonet.pruebatecnicagilapi.repository;

import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MaquinaRepositoryTest {

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Test
    public void testFindByLote() {
        // Primero, inserta algunos datos de prueba
        Maquina maquina1 = new Maquina();
        maquina1.setLote("loteA");
        Maquina maquina2 = new Maquina();
        maquina2.setLote("loteB");
        maquinaRepository.save(maquina1);
        maquinaRepository.save(maquina2);

        // Luego, prueba el método findByLote
        assertEquals(1, maquinaRepository.findByLote("loteA").size());
        assertEquals(maquina1, maquinaRepository.findByLote("loteA").get(0));
    }

}
