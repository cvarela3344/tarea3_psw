
package inf331.jUnit.service;

import inf331.jUnit.model.Cliente;
import inf331.jUnit.model.Compra;
import inf331.jUnit.model.Nivel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompraServiceTest {

    @Test
    void registrarCompraDebeCalcularPuntosSegunNivel() {
        ClienteService clienteService = new ClienteService();
        clienteService.agregarCliente("1", "Ana", "ana@mail.com");

        CompraService compraService = new CompraService(clienteService);

        Compra compra = compraService.registrarCompra("C1", "1", 450, LocalDate.now());

        assertEquals("C1", compra.getIdCompra());
        assertEquals(4, compra.getPuntosOtorgados());
    }

    @Test
    void alRegistrarCompraDebeActualizarNivelDelClienteSiAlcanzaUmbral() {
        ClienteService clienteService = new ClienteService();
        clienteService.agregarCliente("1", "Sofía", "sofia@mail.com");

        CompraService compraService = new CompraService(clienteService);

        compraService.registrarCompra("C100", "1", 100_000, LocalDate.now());

        var cliente = clienteService.obtenerClientePorId("1");

        assertEquals(1000, cliente.getPuntos());
        assertEquals(Nivel.PLATA, cliente.getNivel());  // Debe subir a PLATA
    }

    @Test
    void bonoSeOtorgaCuandoClienteRealizaTresComprasElMismoDia() {
        ClienteService clienteService = new ClienteService();
        clienteService.agregarCliente("1", "Leo", "leo@mail.com");

        CompraService compraService = new CompraService(clienteService);
        LocalDate hoy = LocalDate.now();

        // Realizamos 2 compras sin bono
        compraService.registrarCompra("C1", "1", 100, hoy); // 1 pto
        compraService.registrarCompra("C2", "1", 100, hoy); // 1 pto

        // Tercera compra — debe dar 1 + 10 pts
        Compra tercera = compraService.registrarCompra("C3", "1", 100, hoy);

        assertEquals(11, tercera.getPuntosOtorgados()); // 1 base + 10 bonus

        Cliente cliente = clienteService.obtenerClientePorId("1");
        assertEquals(13, cliente.getPuntos()); // 1 + 1 + 11
    }

    @Test
    void listarComprasDebeRetornarTodasLasComprasRegistradas() {
        ClienteService clienteService = new ClienteService();
        clienteService.agregarCliente("1", "Leo", "leo@mail.com");

        CompraService compraService = new CompraService(clienteService);
        compraService.registrarCompra("C1", "1", 100, LocalDate.now());
        compraService.registrarCompra("C2", "1", 200, LocalDate.now());

        var compras = compraService.listarCompras();
        assertEquals(2, compras.size());
    }

    @Test
    void obtenerCompraPorIdDebeRetornarCompraCorrecta() {
        ClienteService clienteService = new ClienteService();
        clienteService.agregarCliente("1", "Leo", "leo@mail.com");

        CompraService compraService = new CompraService(clienteService);
        compraService.registrarCompra("C1", "1", 100, LocalDate.now());

        var compra = compraService.obtenerCompraPorId("C1");
        assertEquals("1", compra.getIdCliente());
        assertEquals(100, compra.getMonto());
    }

    @Test
    void eliminarCompraDebeRemoverCompraDelRegistro() {
        ClienteService clienteService = new ClienteService();
        clienteService.agregarCliente("1", "Leo", "leo@mail.com");

        CompraService compraService = new CompraService(clienteService);
        compraService.registrarCompra("C1", "1", 100, LocalDate.now());

        compraService.eliminarCompra("C1");

        assertEquals(0, compraService.listarCompras().size());
    }


}
