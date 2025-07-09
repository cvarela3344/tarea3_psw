
package inf331.jUnit.repository;

import inf331.jUnit.model.Cliente;

import java.util.*;

public class EnMemoriaClienteRepository implements ClienteRepository {

    private final Map<String, Cliente> almacenamiento = new HashMap<>();

    @Override
    public void guardar(Cliente cliente) {
        almacenamiento.put(cliente.getId(), cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(String id) {
        return Optional.ofNullable(almacenamiento.get(id));
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(almacenamiento.values());
    }

    @Override
    public void eliminar(String id) {
        almacenamiento.remove(id);
    }
}
