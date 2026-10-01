package com.faculdade.sensor;

/**
 * Component do Decorator: tanto o sensor básico quanto qualquer sensor
 * "decorado" com funcionalidades extras implementam esta mesma interface,
 * o que permite empilhar decoradores livremente e tratar tudo como um
 * único {@code Sensor}.
 */
public interface Sensor {
    double ler();

    String getDescricao();
}
