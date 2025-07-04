import models.cliente.Cliente;
import repository.Repository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repository repo = new Repository();
        boolean running = true;

        while (running) {
            System.out.println("\n====== MENÚ DEL BANCO ======");
            System.out.println("1. Crear cliente");
            System.out.println("2. Crear cuenta");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Retirar dinero");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Ver datos del cliente");
            System.out.println("7. Salir");
            System.out.println("8. Ver historial general");
            System.out.println("9. Ver historial por cuenta");
            System.out.println("10. Lista de Clientes");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    repo.crearCliente(nombre);
                }
                case 2 -> {
                    System.out.print("Ingrese ID del cliente: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());
                    Cliente cliente = repo.getMapClientes().keySet().stream()
                            .filter(c -> c.getId() == idCliente)
                            .findFirst()
                            .orElse(null);
                    if (cliente == null) {
                        System.out.println("Cliente no encontrado.");
                    } else {
                        System.out.println("Tipo de cuenta: 1) Ahorros  2) Corriente  3) Empresa");
                        int tipo = Integer.parseInt(scanner.nextLine());
                        repo.crearCuenta(cliente, tipo);
                    }
                }
                case 3 -> {
                    System.out.print("ID del cliente: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());
                    System.out.print("Número de cuenta: ");
                    long numCuenta = Long.parseLong(scanner.nextLine());
                    System.out.print("Monto a depositar: ");
                    double monto = Double.parseDouble(scanner.nextLine());
                    repo.deposito(idCliente, monto, numCuenta);
                }
                case 4 -> {
                    System.out.print("ID del cliente: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());
                    System.out.print("Número de cuenta: ");
                    long numCuenta = Long.parseLong(scanner.nextLine());
                    System.out.print("Monto a retirar: ");
                    double monto = Double.parseDouble(scanner.nextLine());
                    repo.retiro(idCliente, monto, numCuenta);
                }
                case 5 -> {
                    System.out.print("ID del cliente: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());
                    Cliente cliente = repo.getMapClientes().keySet().stream()
                            .filter(c -> c.getId() == idCliente)
                            .findFirst()
                            .orElse(null);
                    if (cliente != null) {
                        repo.consultarSaldo(cliente);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                }
                case 6 -> {
                    System.out.print("ID del cliente: ");
                    int idCliente = Integer.parseInt(scanner.nextLine());
                    Cliente cliente = repo.getMapClientes().keySet().stream()
                            .filter(c -> c.getId() == idCliente)
                            .findFirst()
                            .orElse(null);
                    if (cliente != null) {
                        repo.imprimirDatos(cliente);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                }
                case 7 -> {
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    running = false;
                }
                case 8 -> {
                    repo.mostrarHistorialGeneral();
                }
                case 9 -> {
                    System.out.print("Número de cuenta: ");
                    long numCuenta = Long.parseLong(scanner.nextLine());
                    repo.mostrarHistorialPorCuenta(numCuenta);
                }
                case 10 -> {
                    repo.listarClientes();
                }
                default -> System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}