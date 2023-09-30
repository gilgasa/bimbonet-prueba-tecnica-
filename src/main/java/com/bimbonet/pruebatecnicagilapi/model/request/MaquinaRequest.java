package com.bimbonet.pruebatecnicagilapi.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel(description = "Datos requeridos para crear o actualizar una máquina")
public class MaquinaRequest {

    @ApiModelProperty(notes = "Número identificador de la máquina", required = true)
    private String numeroMaquina;

    @ApiModelProperty(notes = "Lote al que pertenece la máquina", required = true)
    private String lote;

    @ApiModelProperty(notes = "Lista de posiciones de productos en la máquina")
    private List<PosicionProductoRequest> productos;

    public MaquinaRequest() {
    }

    public MaquinaRequest(String numeroMaquina, String lote, List<PosicionProductoRequest> productos) {
        this.numeroMaquina = numeroMaquina;
        this.lote = lote;
        this.productos = productos;
    }

    public String getNumeroMaquina() {
        return numeroMaquina;
    }

    public void setNumeroMaquina(String numeroMaquina) {
        this.numeroMaquina = numeroMaquina;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public List<PosicionProductoRequest> getProductos() {
        return productos;
    }

    public void setProductos(List<PosicionProductoRequest> productos) {
        this.productos = productos;
    }

}
