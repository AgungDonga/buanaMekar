/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.buanaMekar.repositories;
import com.example.buanaMekar.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Insane
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    List<User> findAll();
    
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username")String username);
}
