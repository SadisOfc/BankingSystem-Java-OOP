package models.cuenta;

import models.cliente.Cliente;
import services.Transaccionable;

public abstract class CuentaBancaria implements Transaccionable{
    private long numeroCuenta;
    private double saldo;
    private Cliente cliente;
    public CuentaBancaria(long numeroCuenta, double saldo, Cliente cliente){
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }
    public long getNumeroCuenta(){
        return numeroCuenta;
    }
    public double getSaldo(){
        return saldo;
    }
    public Cliente getCliente(){
        return cliente;
    }
    public void setNumeroCuenta(long numeroCuenta){
        this.numeroCuenta = numeroCuenta;
    }
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    @Override
    public abstract void realizarDeposito(double monto);

    @Override
    public abstract void realizarRetiro(double monto);
}
