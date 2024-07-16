package com.todoos.todoos_app.repo;

import com.todoos.todoos_app.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends CrudRepository<Todo,Integer> {
}
