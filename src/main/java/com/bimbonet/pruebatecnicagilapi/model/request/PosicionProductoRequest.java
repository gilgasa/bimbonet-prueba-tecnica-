package com.bimbonet.pruebatecnicagilapi.model.request;

import com.bimbonet.pruebatecnicagilapi.model.entity.PosicionProducto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Datos requeridos para crear o actualizar la posición de un producto en una máquina")
public class PosicionProductoRequest {

    @ApiModelProperty(notes = "Posición específica del producto en la máquina", required = true)
    private String posicion;

    @ApiModelProperty(notes = "Número de unidades disponibles del producto en esa posición", required = true)
    private int unidadesDisponibles;

    @ApiModelProperty(notes = "ID del producto relacionado", required = true)
    private Long productoId;

    public PosicionProductoRequest() {
    }

    public PosicionProductoRequest(String posicion, int unidadesDisponibles, Long productoId) {
        this.posicion = posicion;
        this.unidadesDisponibles = unidadesDisponibles;
        this.productoId = productoId;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

}
