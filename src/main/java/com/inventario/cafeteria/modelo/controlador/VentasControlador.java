package com.inventario.cafeteria.modelo.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.cafeteria.excepciones.ResourceNotFoundException;
import com.inventario.cafeteria.modelo.Detalle;
import com.inventario.cafeteria.modelo.Factura;
import com.inventario.cafeteria.modelo.Producto;
import com.inventario.cafeteria.modelo.repositorio.DetalleRepositorio;
import com.inventario.cafeteria.modelo.repositorio.FacturasRepositorio;
import com.inventario.cafeteria.modelo.repositorio.ProductoRepositorio;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class VentasControlador {

	@Autowired
	private FacturasRepositorio facturaRepositorio;
	@Autowired
	private DetalleRepositorio detalleRepositorio;
	@Autowired
	private ProductoRepositorio repositorioProducto;

	@GetMapping("/mostrar-facturas")
	public List<Factura> listarFacturas() {
		return facturaRepositorio.findAll();
	}

	@PostMapping("/crear-factura")
	public Factura guardarFactura(@RequestBody Factura factura) {

		List<Detalle> listaDetalles = factura.getListaDetalle();
		factura.setListaDetalle(null);
		Factura facturaCreada = facturaRepositorio.save(factura);

		Boolean bol = validarStock(listaDetalles);
		if (bol == false)
			System.out.println("no se puede vender");
		else if (bol == true) {
			actualizarProductosId2(listaDetalles);
			listaDetalles = listaDetalles.stream().map(detalle -> {
				detalle.setFactura(facturaCreada);
				return detalle;
			}).collect(Collectors.toList());
			listaDetalles = detalleRepositorio.saveAll(listaDetalles);

			factura.setListaDetalle(listaDetalles);

			return facturaRepositorio.save(factura);
		}
		return null;
	}

	public Boolean validarStock(List<Detalle> detallesFactura) {

		for (Detalle dt : detallesFactura) {
			Producto prod = obtenerProductosId2(dt.getProducto().getId());

			if (prod.getStock() < dt.getCantidad())
				return false;

		}
		return true;
	}

	// buscar por id producto
	public Producto obtenerProductosId2(Long id) {
		Producto producto = repositorioProducto.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(("No existe el producto con el ID: " + id)));
		return producto;
	}

	// actualizar productos
	public void actualizarProductosId2(List<Detalle> detallesFactura) {
		Producto producto;

		for (Detalle dt : detallesFactura) {

			producto = repositorioProducto.findById(dt.getProducto().getId())
					.orElseThrow(() -> new ResourceNotFoundException(("No existe el producto ")));

			producto.setStock(producto.getStock() - dt.getCantidad());

			repositorioProducto.save(producto);
		}

	}

}
