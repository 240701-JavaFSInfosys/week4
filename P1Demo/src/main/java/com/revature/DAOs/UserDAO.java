package com.revature.DAOs;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*By extending JpaRepository, we get access to various DAO methods that we don't need to write

 JpaRepository takes two generics:
 -The Java Model we intend to perform DB operations with
 -The data type of the Primary Key of that Model */
@Repository //1 of the 4 stereotype annotations. Registers this Interface as a Bean
public interface UserDAO extends JpaRepository<User, Integer> {

    //TODO: make some custom methods

}
