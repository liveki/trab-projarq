package com.bcopstein.CtrlCorredorV1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculaPerformanceService {
  @Autowired
  private EventoRepository eventoRepository;

  public List<Evento> execute(int distancia, int ano) {
    List<Evento> eventos = eventoRepository.findByDistanceAndYear(distancia, ano);

    for (int i = 0; i < eventos.size(); i++) {
      for (int j = 0; j < eventos.size() - 1; j++) {
        if (convertToSeconds(eventos.get(j)) > convertToSeconds(eventos.get(j + 1))) {
          Evento aux = eventos.get(j);
          eventos.set(j, eventos.get(j + 1));
          eventos.set(j + 1, aux);
        }
      }
    }

    int melhorPerformance = 0;
    String nomeEventoA;
    String nomeEventoB;

    // TODO: algoritmo para percorrer de dois em dois eventos (tratar eventos
    // ímpares)
    // TODO: algoritmo para percorrer eventos

    return eventos;
  }

  private long convertToSeconds(Evento evento) {
    long seconds = evento.getSegundos() + (evento.getMinutos() * 60) + (evento.getHoras() * 60 * 60);

    return seconds;
  }
}
