package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorIndustrialTest {

    @Test
    void deveDevolverOValorBrutoSemNenhumaTransformacao() {
        Sensor sensor = new SensorIndustrial("Temperatura", 72.0);

        assertEquals(72.0, sensor.ler());
    }

    @Test
    void descricaoDeveIncluirOTipoDoSensor() {
        Sensor sensor = new SensorIndustrial("Pressao", 5.0);

        assertEquals("Sensor Pressao", sensor.getDescricao());
    }
}
