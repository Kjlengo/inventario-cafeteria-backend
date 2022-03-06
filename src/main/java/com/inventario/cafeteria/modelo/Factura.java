package com.inventario.cafeteria.modelo;

import java.sql.Date;
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
@Table(name = "factura")
public class Factura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "fecha_venta")
	private Date fecha_venta;

	@Column(name = "total_venta")
	private int total_venta;

	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Detalle> listaDetalle;

	public Factura() {
		super();
	}

	public Factura(int id, Date fecha_venta, int total_venta, List<Detalle> listaDetalle) {
		super();
		this.id = id;
		this.fecha_venta = fecha_venta;
		this.total_venta = total_venta;
		this.listaDetalle = listaDetalle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public int getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(int total_venta) {
		this.total_venta = total_venta;
	}

	public List<Detalle> getListaDetalle() {
		return listaDetalle;
	}

	public void setListaDetalle(List<Detalle> listaDetalle) {
		this.listaDetalle = listaDetalle;
	}

}
