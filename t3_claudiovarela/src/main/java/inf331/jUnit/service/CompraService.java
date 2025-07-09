
package inf331.jUnit.service;

import inf331.jUnit.model.Cliente;
import inf331.jUnit.model.Compra;
import inf331.jUnit.model.Nivel;
import inf331.jUnit.repository.CompraRepository;
import inf331.jUnit.repository.EnMemoriaCompraRepository;

import java.time.LocalDate;
import java.util.List;

public class CompraService {

    private final ClienteService clienteService;
    private final CompraRepository compraRepository;

    public CompraService(ClienteService clienteService) {
        this.clienteService = clienteService;
        this.compraRepository = new EnMemoriaCompraRepository();
    }

    // Para inyección de dependencias / testing
    public CompraService(ClienteService clienteService, CompraRepository compraRepository) {
        this.clienteService = clienteService;
        this.compraRepository = compraRepository;
    }

    public Compra registrarCompra(String idCompra, String idCliente, int monto, LocalDate fecha) {
        Cliente cliente = clienteService.obtenerClientePorId(idCliente);

        int puntosBase = monto / 100;
        double multiplicador = obtenerMultiplicador(cliente.getNivel());
        int puntosFinales = (int) Math.floor(puntosBase * multiplicador);

        // Calcular compras previas en la misma fecha
        long comprasHoy = compraRepository.listar().stream()
                .filter(c -> c.getIdCliente().equals(idCliente))
                .filter(c -> c.getFecha().isEqual(fecha))
                .count();

        if (comprasHoy == 2) { // esta es la tercera
            puntosFinales += 10;
        }

        cliente.sumarPuntos(puntosFinales);

        Compra compra = new Compra(idCompra, idCliente, monto, fecha, puntosFinales);
        compraRepository.guardar(compra);
        return compra;
    }


    public Compra obtenerCompraPorId(String idCompra) {
        return compraRepository.buscarPorId(idCompra)
                .orElseThrow(() -> new IllegalArgumentException("Compra no encontrada"));
    }

    public void eliminarCompra(String idCompra) {
        if (compraRepository.buscarPorId(idCompra).isEmpty()) {
            throw new IllegalArgumentException("Compra no encontrada");
        }
        compraRepository.eliminar(idCompra);
    }


    public List<Compra> listarCompras() {
        return compraRepository.listar();
    }

    private double obtenerMultiplicador(Nivel nivel) {
        return switch (nivel) {
            case BRONCE -> 1.0;
            case PLATA -> 1.2;
            case ORO -> 1.5;
            case PLATINO -> 2.0;
        };
    }
}
