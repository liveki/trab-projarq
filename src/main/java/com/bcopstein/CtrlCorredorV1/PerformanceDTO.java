package com.bcopstein.CtrlCorredorV1;

public class PerformanceDTO {
  private String provaA;
  private String provaB;

  public PerformanceDTO(String provaA, String provaB) {
    this.provaA = provaA;
    this.provaB = provaB;
  }

  public String getProvaA() {
    return provaA;
  }

  public String getProvaB() {
    return provaB;
  }
}