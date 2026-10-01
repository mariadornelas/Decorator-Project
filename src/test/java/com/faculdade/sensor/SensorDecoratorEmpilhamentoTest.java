package com.faculdade.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa o que realmente caracteriza o Decorator: decoradores podem ser
 * empilhados em qualquer combinação e em qualquer ordem, sempre
 * respeitando a mesma interface {@link Sensor} — e a ORDEM em que são
 * empilhados pode mudar o resultado final.
 */
class SensorDecoratorEmpilhamentoTest {

    @Test
    void ordemDosDecoradoresDeveAlterarOResultadoFinal() {
        Sensor baseNoLimite = new SensorIndustrial("Temperatura", 95.0);

        Sensor calibraDepoisLimita = new DecoradorLimitador(
                new DecoradorCalibragem(baseNoLimite, 15.0),
                0.0, 100.0);

        Sensor limitaDepoisCalibra = new DecoradorCalibragem(
                new DecoradorLimitador(baseNoLimite, 0.0, 100.0),
                15.0);

        // calibrar (95+15=110) e DEPOIS limitar a 100 -> satura em 100
        assertEquals(100.0, calibraDepoisLimita.ler());
        // limitar (95 já está dentro, fica 95) e DEPOIS calibrar -> 110, sem saturar
        assertEquals(110.0, limitaDepoisCalibra.ler());

        assertNotEquals(calibraDepoisLimita.ler(), limitaDepoisCalibra.ler());
    }

    @Test
    void decoradoresDevemPoderSerCombinadosEmQualquerQuantidade() {
        Sensor sensorComTresDecoradores = new DecoradorRegistroAlertas(
                new DecoradorLimitador(
                        new DecoradorCalibragem(new SensorIndustrial("Temperatura", 90.0), 5.0),
                        0.0, 100.0),
                80.0);

        double leitura = assertDoesNotThrow(sensorComTresDecoradores::ler);

        assertEquals(95.0, leitura);
    }

    @Test
    void getDescricaoDeveRefletirAOrdemDeEmpacotamentoDeDentroParaFora() {
        Sensor sensor = new DecoradorLimitador(
                new DecoradorCalibragem(new SensorIndustrial("Temperatura", 70.0), 5.0),
                0.0, 100.0);

        String descricao = sensor.getDescricao();

        int posicaoBase = descricao.indexOf("Sensor Temperatura");
        int posicaoCalibragem = descricao.indexOf("Calibragem");
        int posicaoLimitador = descricao.indexOf("Limitador");

        assertTrue(posicaoBase < posicaoCalibragem);
        assertTrue(posicaoCalibragem < posicaoLimitador);
    }

    @Test
    void clienteDeveFuncionarConhecendoApenasAInterfaceSensor() {
        // Este teste só declara variáveis do tipo Sensor — nunca uma
        // classe decoradora concreta — provando que o código cliente
        // está desacoplado das implementações.
        Sensor[] combinacoesPossiveis = new Sensor[] {
                new SensorIndustrial("Temperatura", 50.0),
                new DecoradorCalibragem(new SensorIndustrial("Temperatura", 50.0), 2.0),
                new DecoradorLimitador(new SensorIndustrial("Temperatura", 150.0), 0.0, 100.0),
                new DecoradorRegistroAlertas(
                        new DecoradorCalibragem(new SensorIndustrial("Temperatura", 50.0), 2.0), 40.0),
        };

        for (Sensor sensor : combinacoesPossiveis) {
            assertDoesNotThrow(sensor::ler);
            assertNotNull(sensor.getDescricao());
        }
    }
}
