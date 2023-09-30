package com.bimbonet.pruebatecnicagilapi.controller;

import com.bimbonet.pruebatecnicagilapi.model.dto.MaquinaDTO;
import com.bimbonet.pruebatecnicagilapi.model.dto.ProductoDTO;
import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import com.bimbonet.pruebatecnicagilapi.model.request.MaquinaRequest;
import com.bimbonet.pruebatecnicagilapi.model.request.ProductoRequest;
import com.bimbonet.pruebatecnicagilapi.service.MaquinaService;
import com.bimbonet.pruebatecnicagilapi.service.ProductoService;
import com.bimbonet.pruebatecnicagilapi.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BimboController.class)
public class BimboControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private MaquinaService maquinaService;

    @MockBean
    private Mapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testListarProductos() throws Exception {
        List<Producto> productos = Arrays.asList(new Producto());
        List<ProductoDTO> productoDTOs = productos.stream().map(mapper::toProductoDTO).collect(Collectors.toList());
        when(productoService.obtenerTodosProductos()).thenReturn(productos);

        mockMvc.perform(get("/api/bimbo/producto"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productoDTOs)));
    }

    @Test
    public void testObtenerProductoExistente() throws Exception {
        Long productoId = 1L;
        Producto producto = new Producto();
        ProductoDTO productoDTO = new ProductoDTO();
        when(productoService.obtenerProductoPorId(productoId)).thenReturn(Optional.of(producto));
        when(mapper.toProductoDTO(producto)).thenReturn(productoDTO);

        mockMvc.perform(get("/api/bimbo/producto/" + productoId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productoDTO)));
    }

    @Test
    public void testObtenerProductoNoExistente() throws Exception {
        Long productoId = 1L;
        when(productoService.obtenerProductoPorId(productoId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/bimbo/producto/" + productoId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEliminarProducto() throws Exception {
        Long productoId = 1L;
        doNothing().when(productoService).eliminarProducto(productoId);

        mockMvc.perform(delete("/api/bimbo/producto/" + productoId))
                .andExpect(status().isNoContent());

        verify(productoService, times(1)).eliminarProducto(productoId);
    }

    @Test
    public void testListarMaquinas() throws Exception {
        List<Maquina> maquinas = Arrays.asList(new Maquina());
        List<MaquinaDTO> maquinaDTOs = maquinas.stream().map(mapper::toMaquinaDTO).collect(Collectors.toList());
        when(maquinaService.obtenerTodasMaquinas()).thenReturn(maquinas);

        mockMvc.perform(get("/api/bimbo/maquina"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(maquinaDTOs)));
    }

    @Test
    public void testObtenerMaquinaExistente() throws Exception {
        Long maquinaId = 1L;
        Maquina maquina = new Maquina();
        MaquinaDTO maquinaDTO = new MaquinaDTO();
        when(maquinaService.obtenerMaquinaPorId(maquinaId)).thenReturn(Optional.of(maquina));
        when(mapper.toMaquinaDTO(maquina)).thenReturn(maquinaDTO);

        mockMvc.perform(get("/api/bimbo/maquina/" + maquinaId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(maquinaDTO)));
    }

    @Test
    public void testObtenerMaquinaNoExistente() throws Exception {
        Long maquinaId = 1L;
        when(maquinaService.obtenerMaquinaPorId(maquinaId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/bimbo/maquina/" + maquinaId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEliminarMaquina() throws Exception {
        Long maquinaId = 1L;
        doNothing().when(maquinaService).eliminarMaquina(maquinaId);

        mockMvc.perform(delete("/api/bimbo/maquina/" + maquinaId))
                .andExpect(status().isNoContent());

        verify(maquinaService, times(1)).eliminarMaquina(maquinaId);
    }

    @Test
    public void testObtenerValorMonetarioMaquinaExistente() throws Exception {
        Long maquinaId = 1L;
        Maquina maquina = new Maquina();
        double valor = 100.0;
        when(maquinaService.obtenerMaquinaPorId(maquinaId)).thenReturn(Optional.of(maquina));
        when(maquinaService.calcularValorMonetario(maquina)).thenReturn(valor);

        mockMvc.perform(get("/api/bimbo/maquina/" + maquinaId + "/valor"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(valor)));
    }

    @Test
    public void testObtenerValorMonetarioMaquinaNoExistente() throws Exception {
        Long maquinaId = 1L;
        when(maquinaService.obtenerMaquinaPorId(maquinaId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/bimbo/maquina/" + maquinaId + "/valor"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testObtenerMaquinasPorValorMonetario() throws Exception {
        List<Maquina> maquinas = Arrays.asList(new Maquina());
        List<MaquinaDTO> maquinaDTOs = maquinas.stream().map(mapper::toMaquinaDTO).collect(Collectors.toList());
        when(maquinaService.obtenerMaquinasPorValorMonetario()).thenReturn(maquinas);

        mockMvc.perform(get("/api/bimbo/maquinasPorValor"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(maquinaDTOs)));
    }

    @Test
    public void testActualizarStockProductoEnMaquina() throws Exception {
        Long maquinaId = 1L;
        Long productoId = 1L;
        int nuevoStock = 5;

        doNothing().when(maquinaService).actualizarStockProducto(maquinaId, productoId, nuevoStock);

        mockMvc.perform(put("/api/bimbo/maquina/" + maquinaId + "/producto/" + productoId + "/actualizarStock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoStock)))
                .andExpect(status().isNoContent());

        verify(maquinaService, times(1)).actualizarStockProducto(maquinaId, productoId, nuevoStock);
    }

}
