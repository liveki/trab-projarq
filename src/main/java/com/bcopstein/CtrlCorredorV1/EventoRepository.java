package com.bcopstein.CtrlCorredorV1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventoRepository implements Repository<Evento> {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public EventoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;

    this.jdbcTemplate.execute("DROP TABLE eventos IF EXISTS");
    this.jdbcTemplate.execute("CREATE TABLE eventos("
        + "id int, nome VARCHAR(255), dia int, mes int, ano int, distancia int, horas int, minutos int, segundos int,PRIMARY KEY(id))");
  }

  @Override
  public boolean save(Evento evento) {
    this.jdbcTemplate.update(
        "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES (?,?,?,?,?,?,?,?,?)",
        evento.getId(), evento.getNome(), evento.getDia(), evento.getMes(), evento.getAno(), evento.getDistancia(),
        evento.getHoras(), evento.getMinutos(), evento.getSegundos());
    return true;
  }

  @Override
  public void update(Evento data) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Evento> findAll() {
    List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos",
        (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
            rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"), rs.getInt("segundos")));

    return resp;
  }

  @Override
  public boolean delete(Evento data) {
    // TODO Auto-generated method stub
    return false;
  }

  public List<Evento> findByDistance(int distancia) {
    return this.jdbcTemplate.query("SELECT * from eventos WHERE distancia=?",
        (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
            rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"), rs.getInt("segundos")),
        distancia);
  }

  public List<Evento> findByDistanceAndYear(int distancia, int ano) {
    return this.jdbcTemplate.query("SELECT * from eventos WHERE distancia=? AND ano=?",
        (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
            rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"), rs.getInt("segundos")),
        distancia, ano);
  }

}
