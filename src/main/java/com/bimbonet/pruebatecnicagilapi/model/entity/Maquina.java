package com.bimbonet.pruebatecnicagilapi.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "maquina")
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroMaquina;

    private String lote;

    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PosicionProducto> productos = new ArrayList<>();

    public void setPosiciones(List<PosicionProducto> productos) {
        this.productos = productos;
        for (PosicionProducto pp : productos) {
            pp.setMaquina(this);  // Establece la relación bidireccional
        }
    }

    public Maquina() {
    }

    public Maquina(String numeroMaquina, String lote) {
        this.numeroMaquina = numeroMaquina;
        this.lote = lote;
        this.productos = new ArrayList<>();  // Inicializamos la lista
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

    public List<PosicionProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<PosicionProducto> productos) {
        this.productos = productos;
    }

}
