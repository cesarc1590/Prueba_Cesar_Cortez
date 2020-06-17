package com.sorteo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sorteo.models.Tipos;

@Repository
public interface tiposInterface extends CrudRepository<Tipos, Long> {

}
