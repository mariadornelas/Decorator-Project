package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoradorRegistroAlertasTest {

    @Test
    void naoDeveRegistrarAlertaQuandoValorEstaAbaixoDoLimite() {
        DecoradorRegistroAlertas sensor = new DecoradorRegistroAlertas(
                new SensorIndustrial("Temperatura", 50.0), 80.0);

        sensor.ler();

        assertTrue(sensor.getAlertasRegistrados().isEmpty());
    }

    @Test
    void deveRegistrarAlertaQuandoValorAtingeOuUltrapassaOLimite() {
        DecoradorRegistroAlertas sensor = new DecoradorRegistroAlertas(
                new SensorIndustrial("Temperatura", 95.0), 80.0);

        sensor.ler();

        assertEquals(1, sensor.getAlertasRegistrados().size());
        assertTrue(sensor.getAlertasRegistrados().get(0).contains("95"));
    }

    @Test
    void naoDeveAlterarOValorLido() {
        DecoradorRegistroAlertas sensor = new DecoradorRegistroAlertas(
                new SensorIndustrial("Temperatura", 95.0), 80.0);

        assertEquals(95.0, sensor.ler());
    }

    @Test
    void listaDeAlertasRetornadaDeveSerSomenteLeitura() {
        DecoradorRegistroAlertas sensor = new DecoradorRegistroAlertas(
                new SensorIndustrial("Temperatura", 95.0), 80.0);
        sensor.ler();

        assertThrows(UnsupportedOperationException.class,
                () -> sensor.getAlertasRegistrados().add("outro"));
    }

    @Test
    void cadaChamadaAlerta_adicionaUmNovoRegistro() {
        DecoradorRegistroAlertas sensor = new DecoradorRegistroAlertas(
                new SensorIndustrial("Temperatura", 95.0), 80.0);

        sensor.ler();
        sensor.ler();

        assertEquals(2, sensor.getAlertasRegistrados().size());
    }
}
