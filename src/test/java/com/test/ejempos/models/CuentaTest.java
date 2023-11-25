package com.test.ejempos.models;

import com.test.ejempos.excepcions.DineroInsuficiente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {


    Cuenta cuenta_1;
    Cuenta cuenta_2;

    @BeforeEach
    void init(){
        this.cuenta_1 = new Cuenta("Alexis", new BigDecimal(1000));
        this.cuenta_2 =new Cuenta("Laura", new BigDecimal(1000));;
    }


    @Test
    void nombreCuenta() {


        cuenta_1.setPersona("Alexis");
        String esperado = "Alexis";
        String real = cuenta_1.getPersona();

        Assertions.assertEquals(real, esperado);
        Assertions.assertTrue(real.equals("Alexis"));
    }

    @Test
    void TestSaldoCuenta() {


        assertEquals(1000, cuenta_1.getSaldo().doubleValue());
        assertFalse(cuenta_1.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta_1.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void TestRefereciaCuenta() {

      // assertNotEquals(cuenta,cuenta2);

        assertEquals(cuenta_1, cuenta_1);
    }

    @Test
    void testDebitoCuenta() {

        cuenta_1.debito(new BigDecimal(100));
        assertNotNull(cuenta_1.getSaldo());
        assertEquals(900, cuenta_1.getSaldo().intValue());

    }

    @Test
    void testCreditoCuenta() {

        cuenta_1.credito(new BigDecimal(100));
        assertNotNull(cuenta_1.getSaldo());
        assertEquals(1100, cuenta_1.getSaldo().intValue());

    }

    @Test
    void DineroInsuficienteExcepcions() {

        Exception exception = assertThrows(DineroInsuficiente.class, () -> {
            cuenta_1.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperando = "Dinero Insuficiente";

        assertEquals(actual, esperando);

    }

    @Test
    void TransferenciaDinero() {


        Banco banco = new Banco();

        banco.setNombre("ScotiaBank");
        banco.transferir(cuenta_2, cuenta_1, new BigDecimal(500));

        assertEquals("500", cuenta_2.getSaldo().toPlainString());
        assertEquals("1500", cuenta_1.getSaldo().toPlainString());


    }


    @Test
    void TransferenciaDineroAddCuentas() {


        Banco banco = new Banco();
        banco.addCuentas(cuenta_1);
        banco.addCuentas(cuenta_2);

        banco.setNombre("ScotiaBank");
        banco.transferir(cuenta_2, cuenta_1, new BigDecimal(500));

        assertEquals("500", cuenta_2.getSaldo().toPlainString());
        assertEquals("1500", cuenta_1.getSaldo().toPlainString());


        assertEquals(2, banco.getCuentas().size());
        assertEquals("ScotiaBank", cuenta_1.getBanco().getNombre());
        assertEquals("Alexis", banco.getCuentas().stream().filter(c -> c.getPersona().equals("Alexis"))
                .findFirst()
                .get().getPersona());

        assertTrue(banco.getCuentas().stream()
                .anyMatch(c -> c.getPersona().equals("Alexis")));


    }

    @Test
    void TransferenciaDineroAddCuentasAssrAll() {


        Banco banco = new Banco();
        banco.addCuentas(cuenta_1);
        banco.addCuentas(cuenta_2);

        banco.setNombre("ScotiaBank");
        banco.transferir(cuenta_2, cuenta_1, new BigDecimal(500));

        assertAll(() -> {
                    assertEquals("1500", cuenta_1.getSaldo().toPlainString());
                },
                () -> {
                    assertEquals("500", cuenta_2.getSaldo().toPlainString());
                });


    }

}