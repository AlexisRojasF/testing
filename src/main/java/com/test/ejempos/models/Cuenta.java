package com.test.ejempos.models;

import com.test.ejempos.excepcions.DineroInsuficiente;

import java.math.BigDecimal;
import java.util.Objects;

public class Cuenta {

    private String persona;
    private BigDecimal saldo;

    private Banco banco;


    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public Cuenta() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(persona, cuenta.persona) && Objects.equals(saldo, cuenta.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persona, saldo);
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void debito(BigDecimal m){

        BigDecimal nuevoSaldo = this.saldo.subtract(m);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO)< 0){
            throw  new DineroInsuficiente("Dinero Insuficiente");
        }
        this.saldo = this.saldo.subtract(m);


    }

    public void credito(BigDecimal m){
        this.saldo =  this.saldo.add(m);

    }
}
