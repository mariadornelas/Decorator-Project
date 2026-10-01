package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecoradorLimitadorTest {

    @Test
    void deveManterOValorQuandoDentroDaFaixa() {
        Sensor sensor = new DecoradorLimitador(new SensorIndustrial("Pressao", 5.0), 0.0, 10.0);

        assertEquals(5.0, sensor.ler());
    }

    @Test
    void deveSaturarNoMinimoQuandoOValorEstaAbaixoDaFaixa() {
        Sensor sensor = new DecoradorLimitador(new SensorIndustrial("Pressao", -2.0), 0.0, 10.0);

        assertEquals(0.0, sensor.ler());
    }

    @Test
    void deveSaturarNoMaximoQuandoOValorEstaAcimaDaFaixa() {
        Sensor sensor = new DecoradorLimitador(new SensorIndustrial("Pressao", 15.0), 0.0, 10.0);

        assertEquals(10.0, sensor.ler());
    }

    @Test
    void deveLancarExcecaoQuandoMinimoForMaiorQueMaximo() {
        Sensor base = new SensorIndustrial("Pressao", 5.0);

        assertThrows(IllegalArgumentException.class, () -> new DecoradorLimitador(base, 10.0, 0.0));
    }

    @Test
    void descricaoDeveIndicarAFaixaConfigurada() {
        Sensor sensor = new DecoradorLimitador(new SensorIndustrial("Pressao", 5.0), 0.0, 10.0);

        assertTrue(sensor.getDescricao().contains("Limitador[0.0, 10.0]"));
    }
}
