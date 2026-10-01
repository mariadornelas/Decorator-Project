package com.faculdade.sensor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecoradorRegistroAlertas extends SensorDecorator {

    private final double limiteAlerta;
    private final List<String> alertasRegistrados = new ArrayList<>();

    public DecoradorRegistroAlertas(Sensor sensor, double limiteAlerta) {
        super(sensor);
        this.limiteAlerta = limiteAlerta;
    }

    @Override
    public double ler() {
        double valor = sensor.ler();
        if (valor >= limiteAlerta) {
            alertasRegistrados.add(
                    String.format("ALERTA: valor %.1f atingiu/ultrapassou o limite %.1f", valor, limiteAlerta));
        }
        return valor;
    }

    public List<String> getAlertasRegistrados() {
        return Collections.unmodifiableList(alertasRegistrados);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + String.format(" > RegistroAlertas(limite=%.1f)", limiteAlerta);
    }
}
