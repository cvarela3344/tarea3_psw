// src/main/java/inf331/jUnit/repository/CompraRepository.java

package inf331.jUnit.repository;

import inf331.jUnit.model.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraRepository {
    void guardar(Compra compra);
    Optional<Compra> buscarPorId(String idCompra);
    List<Compra> listar();
    void eliminar(String idCompra);
}
