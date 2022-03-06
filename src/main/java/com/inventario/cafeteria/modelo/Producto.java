package com.inventario.cafeteria.modelo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "referencia")
	private String referencia;

	@Column(name = "precio")
	private int precio;

	@Column(name = "peso")
	private double peso;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "stock")
	private int stock;

	@Column(name = "fecha_creacion")
	private Date fecha_creacion;

	public Producto() {
		super();
	}

	public Producto(Long id, String nombre, String referencia, int precio, double peso, String categoria, int stock,
			Date fecha_creacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.referencia = referencia;
		this.precio = precio;
		this.peso = peso;
		this.categoria = categoria;
		this.stock = stock;
		this.fecha_creacion = fecha_creacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

}
