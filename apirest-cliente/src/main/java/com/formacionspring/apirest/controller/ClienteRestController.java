package com.formacionspring.apirest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.formacionspring.apirest.entity.Cliente;
import com.formacionspring.apirest.service.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private ClienteService servicio;
	
	//metodo para mostrar todos los clientes
	@GetMapping({"/clientes","/"})
	public List<Cliente> index(){
		return servicio.mostrarTodos();
	}
	//metodo para mostrar un cliente por id
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable long id) {
		return servicio.mostrarPorId(id);
	}
	//metodo para crear un nuevo cliente
	@PostMapping("/clientes")
	public Cliente create(@RequestBody Cliente cliente) {
		return servicio.guardar(cliente);
	}
	
	@PutMapping("/clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente
			,@PathVariable Long id) {
		//buscar al registro por id y guardo el objeto en clienteUpdate
		Cliente clienteUpdate = servicio.mostrarPorId(id);
		//reemplazo los datos de clienteUpdate por el modelo recibido
		//en @RequesBody
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setApellido(cliente.getApellido());
		clienteUpdate.setEmail(cliente.getEmail());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setCreateAt(cliente.getCreateAt());
		
		//guardo y retorno los datos actualizados
		return servicio.guardar(clienteUpdate);
			
	}
	//Borra un registro de cliente por id
	/*@DeleteMapping("/clientes/{id}")
	public void delete(@PathVariable Long id) {
		servicio.borrar(id);
	}*/
	@DeleteMapping("/clientes/{id}")
	public Cliente delete(@PathVariable Long id) {
		Cliente clienteBorrado = servicio.mostrarPorId(id);
		servicio.borrar(id);
		return clienteBorrado;
	}
	
	
}
