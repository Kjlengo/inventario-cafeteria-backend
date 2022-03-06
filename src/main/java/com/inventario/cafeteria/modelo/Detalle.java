package com.inventario.cafeteria.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle")
public class Detalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
		
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "total")
	private int total;
	
	@ManyToOne
	@JoinColumn(name="producto_id", referencedColumnName="id")
	private Producto producto;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonIgnore
	private Factura factura;

	public Detalle() {
		super();
	}

	public Detalle(int id, int cantidad, int total, Producto producto, Factura factura) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;
		this.factura = factura;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}
