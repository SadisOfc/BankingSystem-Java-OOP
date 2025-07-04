package models.cuenta;

import models.cliente.Cliente;

public class CuentaAhorros extends CuentaBancaria{
    public CuentaAhorros(long numeroCuenta, double saldo, Cliente cliente) {
        super(numeroCuenta, saldo, cliente);
    }
    /*El retiro solo puede hacerse si el monto a retirar es menor o igual al saldo actual.
    Si el monto supera el saldo, se lanza una excepción o se muestra un mensaje de “fondos insuficientes”.*/
    @Override
    public void realizarDeposito(double monto) {
        if(monto>0){
            setSaldo(getSaldo()+monto);
            System.out.println("Se realizó el depósito correctamente.");
        }else{
         System.out.println("Monto inválido, debe ser mayor a 0.");
        }
    }

    @Override
    public void realizarRetiro(double monto) {
        if(monto<=getSaldo() && monto>0){
            setSaldo(getSaldo()-monto);
            System.out.println("Se realizó el retiro Correctamente");
        }else{
            System.out.println("El monto supera el Saldo actual");
        }
    }
}
