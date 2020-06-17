package com.sorteo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sorteo.models.Premio;

@Repository
public interface premioInterface extends CrudRepository<Premio,Long> {

}
