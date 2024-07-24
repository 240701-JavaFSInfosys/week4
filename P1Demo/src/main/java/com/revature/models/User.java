package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity //This makes a Class a DB table in your Database. (Makes a DB ENTITY)
@Table(name = "users") //This lets us set attributes like the name of the table
@Component //make this class a Bean
public class User {

    @Id //This makes the field the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This makes our PK auto-increment
    private int userId;

    //@Column isn't necessary UNLESS you want to set a name or constraints
    //- nullable: not null constraint
    //- unique: unique constraint
    //- columnDefinition: lets you define more complex constraints (like checks)

    @Column(nullable = false, unique = true, columnDefinition = "text check (length(username) > 5)")
    private String username;

    @Column(nullable = false)
    private String password;

    /*One to Many relationship (goes hand in hand wiht the Many to One in the Car Class)

    mappedBy: This refers to the field in the Car class that maps to this field
        -We are indicating what the foreign key is in the Car class. (It's the field called user)

    fetch: talked about this in the Car class. Eager loads the reference on app start

    cascade: This is how we specify what operations cascade down to dependent records
        -CascadeType.ALL: ALL operations cascade down to dependent records (updates/deletes etc.) */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Car> cars;

    //boilerplate code------------------- no args, all args, getter/setter, toString

    public User() {
    }

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
