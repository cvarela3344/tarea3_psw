// src/main/java/inf331/jUnit/repository/ClienteRepository.java

package inf331.jUnit.repository;

import inf331.jUnit.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    void guardar(Cliente cliente);
    Optional<Cliente> buscarPorId(String id);
    List<Cliente> listar();
    void eliminar(String id);
}
