package com.bimbonet.pruebatecnicagilapi.controller;

import com.bimbonet.pruebatecnicagilapi.model.dto.*;
import com.bimbonet.pruebatecnicagilapi.model.entity.Maquina;
import com.bimbonet.pruebatecnicagilapi.model.entity.Producto;
import com.bimbonet.pruebatecnicagilapi.model.request.*;
import com.bimbonet.pruebatecnicagilapi.service.MaquinaService;
import com.bimbonet.pruebatecnicagilapi.service.ProductoService;
import com.bimbonet.pruebatecnicagilapi.util.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controlador principal que maneja las operaciones relacionadas con productos y
 * máquinas de Bimbonet.
 * <p>
 * Este controlador proporciona endpoints para la gestión de productos y
 * máquinas, como crear, listar, obtener detalles, y eliminar.
 * </p>
 *
 * @author Gilberto García
 */
@RestController
@RequestMapping("/api/bimbo")
@Api(value = "Operaciones relacionadas con Productos y Máquinas de Bimbonet")
public class BimboController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BimboController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MaquinaService maquinaService;

    @Autowired
    private Mapper mapper;

    /**
     * Crea un nuevo producto.
     *
     * @param productoRequest DTO con la información del producto a crear.
     * @return ResponseEntity con el ProductoDTO creado y el status HTTP
     * correspondiente.
     */
    @ApiOperation(value = "Crea un nuevo producto y devuelve sus detalles", response = ProductoDTO.class)
    @ApiResponses({
        @ApiResponse(code = 201, message = "Producto creado con éxito")
        ,
            @ApiResponse(code = 400, message = "Solicitud inválida")
        ,
            @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @PostMapping("/producto")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody @ApiParam(value = "Datos del producto a crear") ProductoRequest productoRequest) {
        LOGGER.info("Intentando crear un nuevo producto...");
        Producto producto = mapper.fromProductoRequest(productoRequest);
        Producto nuevoProducto = productoService.guardarProducto(producto);
        LOGGER.info("Producto creado exitosamente con ID: {}", nuevoProducto.getId());
        return new ResponseEntity<>(mapper.toProductoDTO(nuevoProducto), HttpStatus.CREATED);
    }

    /**
     * Lista todos los productos disponibles.
     *
     * @return ResponseEntity con la lista de ProductoDTO y el status HTTP
     * correspondiente.
     */
    @ApiOperation(value = "Lista todos los productos disponibles", response = List.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de productos obtenida con éxito")
        ,
            @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @GetMapping("/producto")
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        LOGGER.info("Obteniendo lista de todos los productos...");
        List<Producto> productos = productoService.obtenerTodosProductos();
        LOGGER.info("Se encontraron {} productos.", productos.size());
        return new ResponseEntity<>(productos.stream().map(mapper::toProductoDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * Obtiene los detalles de un producto específico por ID.
     *
     * @param id ID del producto a obtener.
     * @return ResponseEntity con el ProductoDTO y el status HTTP
     * correspondiente.
     */
    @ApiOperation(value = "Obtiene los detalles de un producto específico por ID", response = ProductoDTO.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Producto encontrado")
        ,
            @ApiResponse(code = 404, message = "Producto no encontrado")
        ,
            @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable @ApiParam(value = "ID del producto a obtener") Long id) {
        LOGGER.info("Consultando detalles del producto con ID: {}", id);
        return productoService.obtenerProductoPorId(id)
                .map(producto -> {
                    LOGGER.info("Producto con ID: {} encontrado.", id);
                    return new ResponseEntity<>(mapper.toProductoDTO(producto), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    LOGGER.warn("Producto con ID: {} no encontrado.", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    /**
     * Elimina un producto específico basado en su ID.
     *
     * @param id El ID del producto que se desea eliminar.
     * @return ResponseEntity<Void> Retorna un ResponseEntity con el status
     * HTTP. 204 (No Content) si la eliminación fue exitosa.
     */
    @ApiOperation(value = "Elimina un producto específico por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Producto eliminado con éxito")
        ,
            @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable @ApiParam(value = "ID del producto a eliminar") Long id) {
        LOGGER.info("Intentando eliminar producto con ID: {}", id);
        productoService.eliminarProducto(id);
        LOGGER.info("Producto con ID: {} eliminado exitosamente.", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Crea una nueva máquina en el sistema.
     *
     * @param maquinaRequest Un DTO que contiene la información necesaria para
     * crear la máquina.
     * @return ResponseEntity<MaquinaDTO> Retorna un ResponseEntity con el DTO
     * de la máquina creada y el status HTTP. 201 (Created) si la creación fue
     * exitosa.
     */
    @ApiOperation(value = "Crea una nueva máquina y devuelve sus detalles", response = MaquinaDTO.class)
    @ApiResponses({
        @ApiResponse(code = 201, message = "Máquina creada con éxito")
        ,
            @ApiResponse(code = 400, message = "Solicitud inválida")
        ,
            @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @PostMapping("/maquina")
    public ResponseEntity<MaquinaDTO> crearMaquina(@RequestBody @ApiParam(value = "Datos de la máquina a crear") MaquinaRequest maquinaRequest) {
        LOGGER.info("Intentando crear una nueva máquina...");
        Maquina maquina = mapper.fromMaquinaRequest(maquinaRequest);
        Maquina nuevaMaquina = maquinaService.guardarMaquina(maquina);
        LOGGER.info("Máquina creada exitosamente con ID: {}", nuevaMaquina.getId());
        return new ResponseEntity<>(mapper.toMaquinaDTO(nuevaMaquina), HttpStatus.CREATED);
    }

    /**
     * Obtiene una lista de todas las máquinas disponibles en el sistema.
     *
     * @return ResponseEntity<List<MaquinaDTO>> Retorna un ResponseEntity con
     * una lista de DTOs de las máquinas y el status HTTP 200 (OK).
     */
    @ApiOperation(value = "Lista todas las máquinas disponibles", response = List.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de máquinas obtenida con éxito")
        ,
        @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @GetMapping("/maquina")
    public ResponseEntity<List<MaquinaDTO>> listarMaquinas() {
        LOGGER.info("Obteniendo lista de todas las máquinas...");
        List<Maquina> maquinas = maquinaService.obtenerTodasMaquinas();
        LOGGER.info("Se encontraron {} máquinas.", maquinas.size());
        return new ResponseEntity<>(maquinas.stream().map(mapper::toMaquinaDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * Obtiene los detalles de una máquina específica basado en su ID.
     *
     * @param id El ID de la máquina que se desea obtener.
     * @return ResponseEntity<MaquinaDTO> Retorna un ResponseEntity con el DTO
     * de la máquina solicitada y el status HTTP 200 (OK) si se encontró, o 404
     * (Not Found) si no se encontró.
     */
    @ApiOperation(value = "Obtiene los detalles de una máquina específica por ID", response = MaquinaDTO.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Máquina encontrada")
        ,
        @ApiResponse(code = 404, message = "Máquina no encontrada")
        ,
        @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @GetMapping("/maquina/{id}")
    public ResponseEntity<MaquinaDTO> obtenerMaquina(@PathVariable @ApiParam(value = "ID de la máquina a obtener") Long id) {
        LOGGER.info("Consultando detalles de la máquina con ID: {}", id);
        return maquinaService.obtenerMaquinaPorId(id)
                .map(maquina -> {
                    LOGGER.info("Máquina con ID: {} encontrada.", id);
                    return new ResponseEntity<>(mapper.toMaquinaDTO(maquina), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    LOGGER.warn("Máquina con ID: {} no encontrada.", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    /**
     * Elimina una máquina específica por ID.
     *
     * @param id ID de la máquina a eliminar.
     * @return ResponseEntity con el status HTTP correspondiente.
     */
    @ApiOperation(value = "Elimina una máquina específica por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Máquina eliminada con éxito")
        ,
        @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @DeleteMapping("/maquina/{id}")
    public ResponseEntity<Void> eliminarMaquina(@PathVariable @ApiParam(value = "ID de la máquina a eliminar") Long id) {
        LOGGER.info("Intentando eliminar máquina con ID: {}", id);
        maquinaService.eliminarMaquina(id);
        LOGGER.info("Máquina con ID: {} eliminada exitosamente.", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Calcula y obtiene el valor monetario total de los productos en una
     * máquina específica.
     *
     * @param id ID de la máquina a calcular su valor monetario.
     * @return ResponseEntity con el valor monetario y el status HTTP
     * correspondiente.
     */
    @ApiOperation(value = "Obtiene el valor monetario total de los productos en una máquina específica", response = Double.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Valor monetario obtenido con éxito")
        ,
        @ApiResponse(code = 404, message = "Máquina no encontrada")
        ,
        @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @GetMapping("/maquina/{id}/valor")
    public ResponseEntity<Double> obtenerValorMonetario(@PathVariable @ApiParam(value = "ID de la máquina para calcular su valor monetario") Long id) {
        LOGGER.info("Calculando el valor monetario de la máquina con ID: {}", id);
        return maquinaService.obtenerMaquinaPorId(id)
                .map(maquina -> {
                    Double valor = maquinaService.calcularValorMonetario(maquina);
                    LOGGER.info("Valor monetario de la máquina con ID: {} es: {}", id, valor);
                    return new ResponseEntity<>(valor, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    LOGGER.warn("Máquina con ID: {} no encontrada al calcular valor monetario.", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    /**
     * Obtiene una lista de todas las máquinas ordenadas por su valor monetario.
     *
     * @return ResponseEntity<List<MaquinaDTO>> Retorna un ResponseEntity con
     * una lista de DTOs de las máquinas ordenadas y el status HTTP 200 (OK).
     */
    @ApiOperation(value = "Lista todas las máquinas ordenadas por su valor monetario", response = List.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de máquinas ordenadas por valor monetario obtenida con éxito")
        ,
        @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @GetMapping("/maquinasPorValor")
    public ResponseEntity<List<MaquinaDTO>> obtenerMaquinasPorValorMonetario() {
        LOGGER.info("Obteniendo lista de máquinas ordenadas por valor monetario...");
        List<Maquina> maquinas = maquinaService.obtenerMaquinasPorValorMonetario();
        LOGGER.info("Se encontraron {} máquinas ordenadas por valor monetario.", maquinas.size());
        return new ResponseEntity<>(maquinas.stream().map(mapper::toMaquinaDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    /**
     * Actualiza el stock de un producto específico en una máquina específica.
     *
     * @param maquinaId El ID de la máquina donde se actualizará el stock.
     * @param productoId El ID del producto cuyo stock se desea actualizar.
     * @param nuevoStock El nuevo valor de stock para el producto.
     * @return ResponseEntity<Void> Retorna un ResponseEntity con el status
     * HTTP. 204 (No Content) si la actualización fue exitosa.
     */
    @ApiOperation(value = "Actualiza el stock de un producto específico en una máquina específica")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Stock actualizado con éxito")
        ,
            @ApiResponse(code = 400, message = "Solicitud inválida")
        ,
            @ApiResponse(code = 500, message = "Error en el servidor")
    })
    @PutMapping("/maquina/{maquinaId}/producto/{productoId}/actualizarStock")
    public ResponseEntity<Void> actualizarStockProductoEnMaquina(
            @PathVariable @ApiParam(value = "ID de la máquina donde se actualizará el stock") Long maquinaId,
            @PathVariable @ApiParam(value = "ID del producto cuyo stock se actualizará") Long productoId,
            @RequestBody @ApiParam(value = "Nuevo valor del stock") int nuevoStock) {
        LOGGER.info("Actualizando stock del producto con ID: {} en la máquina con ID: {}. Nuevo stock: {}", productoId, maquinaId, nuevoStock);
        maquinaService.actualizarStockProducto(maquinaId, productoId, nuevoStock);
        LOGGER.info("Stock del producto con ID: {} en la máquina con ID: {} actualizado exitosamente.", productoId, maquinaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
