package com.revature.models;

import jakarta.persistence.*;

@Entity //This makes a Class a DB table in your Database. (Makes a DB ENTITY)
@Table(name = "users") //This lets us set attributes like the name of the table
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
