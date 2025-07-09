package inf331.jUnit.service;

import inf331.jUnit.model.Cliente;
import inf331.jUnit.model.Nivel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    @Test
    void agregarClienteValidoDebeInicializarConPuntosYCategoriaBronce() {
        ClienteService servicio = new ClienteService();

        Cliente cliente = servicio.agregarCliente("1", "Lorena", "lorena@mail.com");

        assertEquals("1", cliente.getId());
        assertEquals("Lorena", cliente.getNombre());
        assertEquals("lorena@mail.com", cliente.getCorreo());
        assertEquals(0, cliente.getPuntos());
        assertEquals(Nivel.BRONCE, cliente.getNivel());
    }

    @Test
    void agregarClienteConCorreoInvalidoDebeLanzarExcepcion() {
        ClienteService servicio = new ClienteService();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            servicio.agregarCliente("2", "Juan", "juanmail.com");
        });

        assertEquals("Correo inválido", ex.getMessage());

    }


    @Test
    void listarClientesDebeRetornarTodosLosClientesAgregados() {
        ClienteService servicio = new ClienteService();

        servicio.agregarCliente("1", "Lorena", "lorena@mail.com");
        servicio.agregarCliente("2", "Juan", "juan@mail.com");

        var clientes = servicio.listarClientes();

        assertEquals(2, clientes.size());
        assertEquals("Lorena", clientes.get(0).getNombre());
        assertEquals("Juan", clientes.get(1).getNombre());
    }
// src/test/java/com/fidelidad/service/ClienteServiceTest.java

    @Test
    void actualizarClienteDebeCambiarNombreYCorreoCorrectamente() {
        ClienteService servicio = new ClienteService();

        servicio.agregarCliente("1", "Lorena", "lorena@mail.com");

        Cliente actualizado = servicio.actualizarCliente("1", "Lory", "lory@mail.com");

        assertEquals("1", actualizado.getId());
        assertEquals("Lory", actualizado.getNombre());
        assertEquals("lory@mail.com", actualizado.getCorreo());
    }

    @Test
    void actualizarClienteConCorreoInvalidoDebeLanzarExcepcion() {
        ClienteService servicio = new ClienteService();
        servicio.agregarCliente("1", "Lorena", "lorena@mail.com");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            servicio.actualizarCliente("1", "Lory", "lorymail.com");
        });

        assertEquals("Correo inválido", ex.getMessage());
    }

    // src/test/java/inf331/jUnit/service/ClienteServiceTest.java

    @Test
    void eliminarClienteDebeRemoverClienteDeLaLista() {
        ClienteService servicio = new ClienteService();
        servicio.agregarCliente("1", "Ana", "ana@mail.com");
        servicio.agregarCliente("2", "Luis", "luis@mail.com");

        servicio.eliminarCliente("1");

        var clientes = servicio.listarClientes();
        assertEquals(1, clientes.size());
        assertEquals("2", clientes.get(0).getId());
    }

    @Test
    void eliminarClienteInexistenteDebeLanzarExcepcion() {
        ClienteService servicio = new ClienteService();
        servicio.agregarCliente("1", "Ana", "ana@mail.com");

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            servicio.eliminarCliente("999");
        });

        assertEquals("Cliente no encontrado", ex.getMessage());
    }

// src/test/java/inf331/jUnit/service/ClienteServiceTest.java

    @Test
    void obtenerClientePorIdDebeRetornarClienteCorrecto() {
        ClienteService servicio = new ClienteService();
        servicio.agregarCliente("1", "Ana", "ana@mail.com");

        Cliente cliente = servicio.obtenerClientePorId("1");

        assertEquals("Ana", cliente.getNombre());
        assertEquals("ana@mail.com", cliente.getCorreo());
    }

    @Test
    void obtenerClientePorIdInexistenteDebeLanzarExcepcion() {
        ClienteService servicio = new ClienteService();

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            servicio.obtenerClientePorId("999");
        });

        assertEquals("Cliente no encontrado", ex.getMessage());
    }

}
