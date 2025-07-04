package models.cuenta;

import models.cliente.Cliente;

public class CuentaCorriente extends CuentaBancaria{
    public CuentaCorriente(long numeroCuenta, double saldo, Cliente cliente) {
        super(numeroCuenta, saldo, cliente);
    }

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
        if(monto>0 && (getSaldo()-monto)>=-1000000){
            setSaldo(getSaldo()-monto);
            System.out.println("Se realizó el Retiro correctamente.");
        }else{
            System.out.println("Monto inválido.");
        }
    }
}
