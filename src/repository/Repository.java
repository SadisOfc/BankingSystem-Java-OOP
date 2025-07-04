package repository;

import models.cliente.Cliente;
import models.cuenta.CuentaAhorros;
import models.cuenta.CuentaBancaria;
import models.cuenta.CuentaCorriente;
import models.cuenta.CuentaEmpresa;

import java.util.*;
import java.util.stream.Collectors;

public class Repository {
    List<String> transacciones = new ArrayList<>();
    Map<Integer,Cliente> clientesMapita = new LinkedHashMap<>();
    Map<CuentaBancaria, List<String>> historialTransacciones = new LinkedHashMap<>();
    Map<Cliente, List<CuentaBancaria>> mapClientes = new LinkedHashMap<>();

    public Map<Cliente, List<CuentaBancaria>> getMapClientes() {
        return mapClientes;
    }
    private int idCliente=1;
    private long idCuenta=100001;

    public void crearCliente(String nombre){
        Cliente cliente = new Cliente(nombre, idCliente);
        idCliente++;
        mapClientes.put(cliente,new ArrayList<>());
        clientesMapita.put(cliente.getId(),cliente);
        System.out.println("Cliente Creado");
    }
    public void crearCuenta(Cliente cliente, int opcion){
        List<CuentaBancaria> periquito = mapClientes.get(cliente);
        CuentaBancaria cuenta = switch (opcion){
            case 1-> new CuentaAhorros(idCuenta++,0,cliente);
            case 2-> new CuentaCorriente(idCuenta++,0,cliente);
            case 3-> new CuentaEmpresa(idCuenta++,0,cliente);
            default -> null;
        };
        if(cuenta!=null){
            periquito.add(cuenta);
            System.out.println("Cuenta creada con número: " + cuenta.getNumeroCuenta());
        }
    }
    public void deposito(int idCliente, double monto, long idCuenta){
        if(clientesMapita.containsKey(idCliente)){
            List<CuentaBancaria> lista = mapClientes.get(clientesMapita.get(idCliente));
            CuentaBancaria cuenta = lista.stream().filter(a -> a.getNumeroCuenta()==idCuenta).findFirst().orElse(null);
            if(cuenta!=null){
                cuenta.realizarDeposito(monto);
                transacciones.add("Deposito de $" + monto + " en cuenta #"+idCuenta);
                historialTransacciones.computeIfAbsent(cuenta, k->new ArrayList<>()).add("Depósito de $"+monto);
            }else{
                System.out.println("Esa cuenta no existe");
            }
        }else{
            System.out.println("Ese id no existe");
        }
    }
    public void retiro(int idCliente, double monto, long idCuenta){
        if(clientesMapita.containsKey(idCliente)){
            List<CuentaBancaria> lista = mapClientes.get(clientesMapita.get(idCliente));
            CuentaBancaria cuenta = lista.stream().filter(a -> a.getNumeroCuenta()==idCuenta).findFirst().orElse(null);
            if(cuenta!=null){
                cuenta.realizarRetiro(monto);
                transacciones.add("Retiro de $" + monto + " en cuenta #"+idCuenta);
                historialTransacciones.computeIfAbsent(cuenta, k->new ArrayList<>()).add("Retiro de $"+monto);
            }else{
                System.out.println("Esa cuenta no existe");
            }
        }else{
            System.out.println("Ese id no existe");
        }
    }

    public void consultarSaldo(Cliente cliente){
        if(verificarCuenta(cliente)){
            System.out.println("- Saldo " + cliente.getNombre() + " -");
            mapClientes.get(cliente).forEach(a -> System.out.println(a.getNumeroCuenta() + " - " + a.getClass().getSimpleName() + ": " + a.getSaldo()));
        }
    }
    public void imprimirDatos(Cliente cliente){
        System.out.println("- Datos del Usuario -");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Id: " + cliente.getId());
        if(verificarCuenta(cliente)) mapClientes.get(cliente).forEach(a-> System.out.println(a.getNumeroCuenta() + " - " + a.getClass().getSimpleName()));
    }
    public boolean verificarCuenta(Cliente cliente){
        if(mapClientes.containsKey(cliente)){
            return !mapClientes.get(cliente).isEmpty();
        }
        return false;
    }
    public void mostrarHistorialGeneral() {
        if (transacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas.");
            return;
        }
        System.out.println("- Historial General -");
        transacciones.forEach(System.out::println);
    }
    public void mostrarHistorialPorCuenta(long numeroCuenta) {
        CuentaBancaria cuenta = historialTransacciones.keySet().stream()
                .filter(c -> c.getNumeroCuenta() == numeroCuenta)
                .findFirst()
                .orElse(null);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada.");
            return;
        }

        List<String> historial = historialTransacciones.get(cuenta);
        if (historial == null || historial.isEmpty()) {
            System.out.println("No hay transacciones para esta cuenta.");
            return;
        }

        System.out.println("- Historial de la cuenta #" + numeroCuenta + " -");
        historial.forEach(System.out::println);
    }
    public void listarClientes() {
        if (clientesMapita.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("- Lista de clientes -");
        clientesMapita.values().forEach(cliente ->
                System.out.println("ID: " + cliente.getId() + " - Nombre: " + cliente.getNombre())
        );
    }
}
