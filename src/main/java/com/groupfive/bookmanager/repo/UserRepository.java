package com.groupfive.bookmanager.repo;

import com.groupfive.bookmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//Repo for users, we only really need this to define our custom query, we need to be able to
// find users by their userName, but we also use it to populate a table with users in our DB.

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
