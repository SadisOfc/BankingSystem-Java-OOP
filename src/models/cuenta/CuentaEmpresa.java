package models.cuenta;

import models.cliente.Cliente;

public class CuentaEmpresa extends CuentaBancaria{
    public CuentaEmpresa(long numeroCuenta, double saldo, Cliente cliente) {
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
        double comision = monto * 0.01;
        double montoConComision = monto + comision;

        if (monto > 10_000_000) {
            System.out.println("Error: El monto excede el límite de retiro por operación (10 millones).");
        } else if (montoConComision > getSaldo()) {
            System.out.println("Error: Fondos insuficientes para cubrir el monto y la comisión.");
        } else if (monto <= 0) {
            System.out.println("Error: El monto debe ser mayor que cero.");
        } else {
            setSaldo(getSaldo() - montoConComision);
            System.out.println("Retiro exitoso.");
            System.out.println("Comisión aplicada: $" + comision);
            System.out.println("Saldo restante: $" + getSaldo());
        }
    }
}
