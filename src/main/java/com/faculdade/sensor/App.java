package com.faculdade.sensor;

/**
 * Cliente de demonstração. Do início ao fim, o código só conhece a
 * interface {@link Sensor} — nunca precisa saber se está lidando com o
 * componente básico ou com uma pilha de decoradores.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("=== Empilhando decoradores em um SensorIndustrial ===");

        Sensor sensorBasico = new SensorIndustrial("Temperatura", 72.0);

        Sensor sensorCompleto = new DecoradorRegistroAlertas(
                new DecoradorLimitador(
                        new DecoradorCalibragem(sensorBasico, 5.0),
                        0.0, 100.0),
                80.0);

        System.out.println(sensorCompleto.getDescricao());
        System.out.printf("Leitura final: %.1f%n", sensorCompleto.ler());

        DecoradorRegistroAlertas registro = (DecoradorRegistroAlertas) sensorCompleto;
        System.out.println("Alertas registrados: " + registro.getAlertasRegistrados());

        System.out.println();
        System.out.println("=== Prova de que a ordem dos decoradores importa ===");

        Sensor baseNoLimite = new SensorIndustrial("Temperatura", 95.0);

        Sensor calibraDepoisLimita = new DecoradorLimitador(
                new DecoradorCalibragem(baseNoLimite, 15.0),
                0.0, 100.0);

        Sensor limitaDepoisCalibra = new DecoradorCalibragem(
                new DecoradorLimitador(baseNoLimite, 0.0, 100.0),
                15.0);

        System.out.printf("Calibrar (+15) e DEPOIS limitar [0,100]: %.1f%n", calibraDepoisLimita.ler());
        System.out.printf("Limitar [0,100] e DEPOIS calibrar (+15): %.1f%n", limitaDepoisCalibra.ler());
        System.out.println("Mesmos dois decoradores, mesma leitura bruta (95.0), resultados diferentes "
                + "só por causa da ordem de empacotamento.");
    }
}
