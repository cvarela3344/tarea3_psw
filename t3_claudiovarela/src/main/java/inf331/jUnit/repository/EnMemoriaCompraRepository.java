// src/main/java/inf331/jUnit/repository/EnMemoriaCompraRepository.java

package inf331.jUnit.repository;

import inf331.jUnit.model.Compra;

import java.util.*;

public class EnMemoriaCompraRepository implements CompraRepository {

    private final Map<String, Compra> almacenamiento = new HashMap<>();

    @Override
    public void guardar(Compra compra) {
        almacenamiento.put(compra.getIdCompra(), compra);
    }

    @Override
    public Optional<Compra> buscarPorId(String idCompra) {
        return Optional.ofNullable(almacenamiento.get(idCompra));
    }

    @Override
    public List<Compra> listar() {
        return new ArrayList<>(almacenamiento.values());
    }

    @Override
    public void eliminar(String idCompra) {
        almacenamiento.remove(idCompra);
    }
}
