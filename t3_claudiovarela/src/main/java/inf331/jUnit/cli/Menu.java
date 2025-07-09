// src/main/java/inf331/jUnit/app/Menu.java

package inf331.jUnit.app;

import inf331.jUnit.model.Cliente;
import inf331.jUnit.model.Compra;
import inf331.jUnit.model.Nivel;
import inf331.jUnit.service.ClienteService;
import inf331.jUnit.service.CompraService;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

    private final ClienteService clienteService = new ClienteService();
    private final CompraService compraService = new CompraService(clienteService);
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Compras");
            System.out.println("3. Ver puntos y nivel de un cliente");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> menuClientes();
                case "2" -> menuCompras();
                case "3" -> mostrarPuntosYNivel();
                case "4" -> {
                    System.out.println("Saliendo del sistema...");
                    return;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void menuClientes() {
        System.out.println("\n--- Gestión de Clientes ---");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Actualizar cliente");
        System.out.println("4. Eliminar cliente");
        System.out.print("Seleccione opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1" -> agregarCliente();
            case "2" -> listarClientes();
            case "3" -> actualizarCliente();
            case "4" -> eliminarCliente();
            default -> System.out.println("Opción inválida");
        }
    }

    private void menuCompras() {
        System.out.println("\n--- Gestión de Compras ---");
        System.out.println("1. Registrar compra");
        System.out.println("2. Listar compras");
        System.out.println("3. Eliminar compra");
        System.out.print("Seleccione opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1" -> registrarCompra();
            case "2" -> listarCompras();
            case "3" -> eliminarCompra();
            default -> System.out.println("Opción inválida");
        }
    }

    private void agregarCliente() {
        try {
            System.out.print("ID del cliente: ");
            String id = scanner.nextLine();
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Correo: ");
            String correo = scanner.nextLine();

            clienteService.agregarCliente(id, nombre, correo);
            System.out.println("Cliente agregado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarClientes() {
        var clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (Cliente c : clientes) {
                System.out.printf("ID: %s | Nombre: %s | Correo: %s | Puntos: %d | Nivel: %s%n",
                        c.getId(), c.getNombre(), c.getCorreo(), c.getPuntos(), c.getNivel());
            }
        }
    }

    private void actualizarCliente() {
        try {
            System.out.print("ID del cliente a actualizar: ");
            String id = scanner.nextLine();
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo correo: ");
            String correo = scanner.nextLine();

            clienteService.actualizarCliente(id, nombre, correo);
            System.out.println("Cliente actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarCliente() {
        try {
            System.out.print("ID del cliente a eliminar: ");
            String id = scanner.nextLine();

            clienteService.eliminarCliente(id);
            System.out.println("Cliente eliminado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarCompra() {
        try {
            System.out.print("ID de la compra: ");
            String idCompra = scanner.nextLine();
            System.out.print("ID del cliente: ");
            String idCliente = scanner.nextLine();
            System.out.print("Monto de la compra (entero): ");
            int monto = Integer.parseInt(scanner.nextLine());

            Compra compra = compraService.registrarCompra(idCompra, idCliente, monto, LocalDate.now());

            System.out.printf("Compra registrada con %d puntos otorgados.%n", compra.getPuntosOtorgados());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarCompras() {
        var compras = compraService.listarCompras();
        if (compras.isEmpty()) {
            System.out.println("No hay compras registradas.");
        } else {
            for (Compra c : compras) {
                System.out.printf("ID: %s | Cliente: %s | Monto: %d | Fecha: %s | Puntos: %d%n",
                        c.getIdCompra(), c.getIdCliente(), c.getMonto(), c.getFecha(), c.getPuntosOtorgados());
            }
        }
    }

    private void eliminarCompra() {
        try {
            System.out.print("ID de la compra a eliminar: ");
            String id = scanner.nextLine();

            compraService.eliminarCompra(id);
            System.out.println("Compra eliminada.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mostrarPuntosYNivel() {
        try {
            System.out.print("ID del cliente: ");
            String id = scanner.nextLine();
            Cliente cliente = clienteService.obtenerClientePorId(id);
            System.out.printf("Puntos: %d | Nivel: %s%n", cliente.getPuntos(), cliente.getNivel());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
