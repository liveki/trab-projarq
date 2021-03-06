package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrlCorridas")
public class CtrlCorridasControler {

  @Autowired
  private CalculaEstatisticasService calculaEstatisticasService;

  @Autowired
  private CalculaPerformanceService calculaPerformanceService;

  @Autowired
  private CorredorRepository corredorRepository;

  @Autowired
  private EventoRepository eventoRepository;

  @GetMapping("/corredor")
  @CrossOrigin(origins = "*")
  public List<Corredor> consultaCorredor() {
    return corredorRepository.findAll();
  }

  @PostMapping("/corredor")
  @CrossOrigin(origins = "*")
  public boolean cadastraCorredor(@RequestBody final Corredor corredor) {
    return corredorRepository.save(corredor);
  }

  @GetMapping("/eventos")
  @CrossOrigin(origins = "*")
  public List<Evento> consultaEventos() {
    return eventoRepository.findAll();
  }

  @PostMapping("/eventos") // adiciona evento no único corredor
  @CrossOrigin(origins = "*")
  public boolean informaEvento(@RequestBody final Evento evento) {
    return eventoRepository.save(evento);
  }

  @GetMapping("/estatisticas")
  @CrossOrigin(origins = "*")
  public EstatisticasDTO estatisticas(@RequestParam final int distancia) {
    return calculaEstatisticasService.execute(distancia);
  }

  @GetMapping("/aumentoPerformance")
  @CrossOrigin(origins = "*")
  public PerformanceDTO aumentoPerformance(@RequestParam final int distancia, @RequestParam final int ano) {
    return calculaPerformanceService.execute(distancia, ano);
  }
}
