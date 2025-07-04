package services;

public interface Transaccionable {
    public abstract void realizarDeposito(double monto);
    public abstract void realizarRetiro(double monto);
}
