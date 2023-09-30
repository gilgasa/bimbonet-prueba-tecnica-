package com.bimbonet.pruebatecnicagilapi.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Información detallada sobre el producto")
public class ProductoDTO {

    @ApiModelProperty(notes = "ID único del producto")
    private Long id;

    @ApiModelProperty(notes = "SKU del producto")
    private String SKU;

    @ApiModelProperty(notes = "Nombre del producto")
    private String nombre;

    @ApiModelProperty(notes = "Precio del producto")
    private Double precio;

    @ApiModelProperty(notes = "Código de barras del producto")
    private String codigoBarras;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String SKU, String nombre, Double precio, String codigoBarras) {
        this.id = id;
        this.SKU = SKU;
        this.nombre = nombre;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

}
