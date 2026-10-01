package com.faculdade.sensor;

public class DecoradorLimitador extends SensorDecorator {

    private final double minimo;
    private final double maximo;

    public DecoradorLimitador(Sensor sensor, double minimo, double maximo) {
        super(sensor);
        if (minimo > maximo) {
            throw new IllegalArgumentException("minimo não pode ser maior que maximo");
        }
        this.minimo = minimo;
        this.maximo = maximo;
    }

    @Override
    public double ler() {
        double valor = sensor.ler();
        if (valor < minimo) {
            return minimo;
        }
        if (valor > maximo) {
            return maximo;
        }
        return valor;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + String.format(" > Limitador[%.1f, %.1f]", minimo, maximo);
    }
}
