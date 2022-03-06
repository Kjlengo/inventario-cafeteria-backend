package com.inventario.cafeteria.modelo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.cafeteria.modelo.Factura;

@Repository
public interface FacturasRepositorio extends JpaRepository <Factura,Integer>{

}
