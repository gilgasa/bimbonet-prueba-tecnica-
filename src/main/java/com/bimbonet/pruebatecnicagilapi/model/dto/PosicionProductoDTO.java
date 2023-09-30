package com.bimbonet.pruebatecnicagilapi.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Detalles sobre la posición de un producto en una máquina")
public class PosicionProductoDTO {

    @ApiModelProperty(notes = "ID único de la posición del producto")
    private Long id;

    @ApiModelProperty(notes = "Posición del producto en la máquina")
    private String posicion;

    @ApiModelProperty(notes = "Unidades disponibles del producto en esa posición")
    private int unidadesDisponibles;

    @ApiModelProperty(notes = "Detalles del producto en esa posición")
    private ProductoDTO producto;

    public PosicionProductoDTO() {
    }

    public PosicionProductoDTO(Long id, String posicion, int unidadesDisponibles, ProductoDTO producto) {
        this.id = id;
        this.posicion = posicion;
        this.unidadesDisponibles = unidadesDisponibles;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

}
