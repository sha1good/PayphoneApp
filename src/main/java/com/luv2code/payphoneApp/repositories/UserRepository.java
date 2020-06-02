package com.luv2code.payphoneApp.repositories;

import com.luv2code.payphoneApp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

      User findByUsername(String username);
      User getUserById(Long Id);
}
