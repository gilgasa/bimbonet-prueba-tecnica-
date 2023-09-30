package com.bimbonet.pruebatecnicagilapi.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel(description = "Información detallada sobre la máquina")
public class MaquinaDTO {

    @ApiModelProperty(notes = "ID único de la máquina")
    private Long id;

    @ApiModelProperty(notes = "Número identificador de la máquina")
    private String numeroMaquina;

    @ApiModelProperty(notes = "Lote al que pertenece la máquina")
    private String lote;

    @ApiModelProperty(notes = "Posiciones de productos en la máquina")
    private List<PosicionProductoDTO> productos;

    public MaquinaDTO() {
    }

    public MaquinaDTO(Long id, String numeroMaquina, String lote, List<PosicionProductoDTO> productos) {
        this.id = id;
        this.numeroMaquina = numeroMaquina;
        this.lote = lote;
        this.productos = productos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<PosicionProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<PosicionProductoDTO> productos) {
        this.productos = productos;
    }

}
