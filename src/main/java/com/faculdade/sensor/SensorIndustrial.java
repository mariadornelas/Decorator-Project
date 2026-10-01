package com.faculdade.sensor;

/**
 * Concrete Component: o sensor "cru", sem nenhum tratamento sobre a
 * leitura. Representa o hardware bruto — simula o valor que o sensor
 * físico mediu, sem calibragem, sem limitação de faixa, sem registro de
 * alertas. Todo o comportamento extra é adicionado por fora, via
 * decoradores, sem alterar esta classe.
 */
public class SensorIndustrial implements Sensor {

    private final String tipo;
    private final double valorBruto;

    public SensorIndustrial(String tipo, double valorBruto) {
        this.tipo = tipo;
        this.valorBruto = valorBruto;
    }

    @Override
    public double ler() {
        return valorBruto;
    }

    @Override
    public String getDescricao() {
        return "Sensor " + tipo;
    }
}
