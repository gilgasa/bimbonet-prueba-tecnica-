package com.bimbonet.pruebatecnicagilapi.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Datos requeridos para crear o actualizar un producto")
public class ProductoRequest {

    @ApiModelProperty(notes = "SKU del producto", required = true)
    private String SKU;

    @ApiModelProperty(notes = "Nombre del producto", required = true)
    private String nombre;

    @ApiModelProperty(notes = "Precio del producto", required = true)
    private Double precio;

    @ApiModelProperty(notes = "Código de barras del producto", required = true)
    private String codigoBarras;

    public ProductoRequest() {
    }

    public ProductoRequest(String SKU, String nombre, Double precio, String codigoBarras) {
        this.SKU = SKU;
        this.nombre = nombre;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
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
