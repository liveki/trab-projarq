package com.bcopstein.CtrlCorredorV1;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Component
class CorredorRepository implements Repository<Corredor> {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public CorredorRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;

    this.jdbcTemplate.execute("DROP TABLE corredores IF EXISTS");
    this.jdbcTemplate.execute("CREATE TABLE corredores("
        + "cpf VARCHAR(255), nome VARCHAR(255), genero VARCHAR(255), diaDn int, mesDn int, anoDn int, PRIMARY KEY(cpf))");

    this.jdbcTemplate.batchUpdate(
        "INSERT INTO corredores(cpf,nome,genero,diaDn,mesDn,anoDn) VALUES ('10001287','Luiz','masculino',22,5,1987)");
  }

  @Override
  public boolean save(Corredor corredor) {
    // Limpa a base de dados
    this.jdbcTemplate.batchUpdate("DELETE from Corredores");
    // Então cadastra o novo "corredor único"
    this.jdbcTemplate.update("INSERT INTO corredores(cpf,nome,diaDn,mesDn,anoDn,genero) VALUES (?,?,?,?,?,?)",
        corredor.getCpf(), corredor.getNome(), corredor.getDiaDn(), corredor.getMesDn(), corredor.getAnoDn(),
        corredor.getGenero());

    return true;
  }

  @Override
  public void update(Corredor corredor) {
    this.jdbcTemplate.update("UPDATE corredores SET cpf=?, nome=?, diaDn=?, mesDn=?, anoDn=?, genero=? WHERE cpf=?",
        corredor.getCpf(), corredor.getNome(), corredor.getDiaDn(), corredor.getMesDn(), corredor.getAnoDn(),
        corredor.getGenero(), corredor.getCpf());
  }

  @Override
  public List<Corredor> findAll() {
    List<Corredor> resp = this.jdbcTemplate.query("SELECT * from corredores",
        (rs, rowNum) -> new Corredor(rs.getString("cpf"), rs.getString("nome"), rs.getInt("diaDn"), rs.getInt("mesDn"),
            rs.getInt("anoDn"), rs.getString("genero")));
    return resp;
  }

  @Override
  public boolean delete(Corredor corredor) {
    return true;
  }
}