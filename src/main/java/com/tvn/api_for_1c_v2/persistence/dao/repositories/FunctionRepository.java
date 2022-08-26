package com.tvn.api_for_1c_v2.persistence.dao.repositories;

import com.tvn.api_for_1c_v2.persistence.model.Function;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FunctionRepository extends CrudRepository<Function, Long> {

}
