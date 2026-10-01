package com.faculdade.sensor;

import java.util.Objects;

/**
 * Decorator abstrato: implementa {@link Sensor} e guarda uma referência
 * para outro {@code Sensor} (que pode ser o componente concreto ou já um
 * outro decorador). Por padrão, apenas repassa a chamada adiante — cada
 * decorador concreto sobrescreve o que precisa para acrescentar seu
 * próprio comportamento antes ou depois de delegar.
 */
public abstract class SensorDecorator implements Sensor {

    protected final Sensor sensor;

    protected SensorDecorator(Sensor sensor) {
        this.sensor = Objects.requireNonNull(sensor, "sensor não pode ser nulo");
    }

    @Override
    public double ler() {
        return sensor.ler();
    }

    @Override
    public String getDescricao() {
        return sensor.getDescricao();
    }
}
