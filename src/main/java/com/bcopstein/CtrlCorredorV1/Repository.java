package com.bcopstein.CtrlCorredorV1;

import java.util.Collection;

interface Repository<T> {
  public boolean save(T data);

  public Collection<T> findAll();
}