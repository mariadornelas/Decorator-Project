package com.faculdade.sensor;

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
