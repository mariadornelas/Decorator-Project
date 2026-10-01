package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoradorCalibragemTest {

    @Test
    void deveSomarOOffsetPositivoAoValorBruto() {
        Sensor sensor = new DecoradorCalibragem(new SensorIndustrial("Temperatura", 70.0), 5.0);

        assertEquals(75.0, sensor.ler());
    }

    @Test
    void deveSomarOOffsetNegativoAoValorBruto() {
        Sensor sensor = new DecoradorCalibragem(new SensorIndustrial("Temperatura", 70.0), -3.0);

        assertEquals(67.0, sensor.ler());
    }

    @Test
    void deveSerPossivelEmpilharCalibragemDuasVezes() {
        Sensor sensor = new DecoradorCalibragem(
                new DecoradorCalibragem(new SensorIndustrial("Temperatura", 70.0), 5.0),
                2.0);

        assertEquals(77.0, sensor.ler());
    }

    @Test
    void descricaoDeveEncadearAPartirDoSensorDecorado() {
        Sensor sensor = new DecoradorCalibragem(new SensorIndustrial("Temperatura", 70.0), 5.0);

        String descricao = sensor.getDescricao();

        assertTrue(descricao.startsWith("Sensor Temperatura"));
        assertTrue(descricao.contains("Calibragem(+5.0)"));
    }
}
