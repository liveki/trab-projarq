package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculaEstatisticasService {

  @Autowired
  private EventoRepository eventoRepositoy;

  public EstatisticasDTO execute(int distancia) {
    List<Evento> eventos = eventoRepositoy.findByDistance(distancia);

    double mediaMinutos = calculaMedia(eventos);
    double medianaMinutos = calculaMediana(eventos);
    double desvioPadrao = calculaDesvioPadrao(eventos);

    EstatisticasDTO estatisticasDTO = new EstatisticasDTO(mediaMinutos, medianaMinutos, desvioPadrao);

    return estatisticasDTO;
  }

  private double calculaMedia(List<Evento> eventos) {
    double mediaMinutos = 0;

    for (Evento evento : eventos) {
      mediaMinutos += evento.getMinutos();
    }

    mediaMinutos = mediaMinutos / eventos.size();

    return mediaMinutos;
  }

  private double calculaMediana(List<Evento> eventos) {
    double minutos = 0;

    if (eventos.size() % 2 != 0) {
      minutos = eventos.get(eventos.size() / 2).getMinutos();

      return minutos;
    }

    minutos = (eventos.get(eventos.size() / 2).getMinutos() + eventos.get((eventos.size() / 2) - 1).getMinutos()) / 2;

    return minutos;
  }

  private double calculaDesvioPadrao(List<Evento> eventos) {
    double desvioPadrao = 0;
    double somaPotencias = 0;
    double mediaAritmetica = calculaMedia(eventos);

    for (Evento evento : eventos) {
      somaPotencias += Math.pow(evento.getMinutos() - mediaAritmetica, 2);
    }

    desvioPadrao = Math.sqrt(somaPotencias / eventos.size());

    return desvioPadrao;
  }
}
