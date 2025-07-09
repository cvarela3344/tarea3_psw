// src/main/java/inf331/jUnit/service/ClienteService.java

package inf331.jUnit.service;

import inf331.jUnit.model.Cliente;
import inf331.jUnit.model.Nivel;
import inf331.jUnit.repository.ClienteRepository;
import inf331.jUnit.repository.EnMemoriaClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository repositorio;

    public ClienteService() {
        this.repositorio = new EnMemoriaClienteRepository();
    }

    // Para pruebas o inyección de dependencias
    public ClienteService(ClienteRepository repo) {
        this.repositorio = repo;
    }

    public Cliente agregarCliente(String id, String nombre, String correo) {
        validarCorreo(correo);
        Cliente cliente = new Cliente(id, nombre, correo);
        repositorio.guardar(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return repositorio.listar();
    }

    public Cliente actualizarCliente(String id, String nuevoNombre, String nuevoCorreo) {
        validarCorreo(nuevoCorreo);
        Cliente cliente = obtenerClientePorId(id);
        cliente.setNombre(nuevoNombre);
        cliente.setCorreo(nuevoCorreo);
        repositorio.guardar(cliente);
        return cliente;
    }

    public void eliminarCliente(String id) {
        if (repositorio.buscarPorId(id).isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        repositorio.eliminar(id);
    }

    public Cliente obtenerClientePorId(String id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }

    private void validarCorreo(String correo) {
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("Correo inválido");
        }
    }
}
