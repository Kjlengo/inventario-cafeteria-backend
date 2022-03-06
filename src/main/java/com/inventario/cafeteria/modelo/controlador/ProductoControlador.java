package com.inventario.cafeteria.modelo.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.cafeteria.excepciones.ResourceNotFoundException;
import com.inventario.cafeteria.modelo.Producto;
import com.inventario.cafeteria.modelo.repositorio.ProductoRepositorio;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // el puerto donde esta corriendo el front
public class ProductoControlador {

	@Autowired
	private ProductoRepositorio repositorio;

	// METODO PARA LISTAR PRODUCTOS
	@GetMapping("/productos")
	public List<Producto> listarProductos() {
		return repositorio.findAll();
	}

	// METODO PARA GUARDAR PRODUCTO
	@PostMapping("/productos")
	public Producto guardarProducto(@RequestBody Producto producto) {
		return repositorio.save(producto);
	}

	// METODO PARA BUSCAR UN PRODUCTO POR ID
	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> obtenerProductosId(@PathVariable Long id) {
		Producto producto = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(("No existe el producto con el ID: " + id)));
		return ResponseEntity.ok(producto);
	}

	// METODO PARA ACTUALIZAR PRODUCTO
	@PutMapping("/productos/{id}")
	public ResponseEntity<Producto> actualizarProductosId(@PathVariable Long id,
			@RequestBody Producto detallesProducto) {
		Producto producto = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(("No existe el producto con el ID: " + id)));

		producto.setNombre(detallesProducto.getNombre());
		producto.setReferencia(detallesProducto.getReferencia());
		producto.setPrecio(detallesProducto.getPrecio());
		producto.setPeso(detallesProducto.getPeso());
		producto.setCategoria(detallesProducto.getCategoria());
		producto.setStock(detallesProducto.getStock());
		producto.setFecha_creacion(detallesProducto.getFecha_creacion());

		Producto productoActualizado = repositorio.save(producto);
		return ResponseEntity.ok(productoActualizado);
	}

	// METODO PARA ELIMINAR PRODUCTO
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Long id) {
		Producto producto = repositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el ID : " + id));

		repositorio.delete(producto);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar", Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
