package com.revature.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component //Makes a Class a bean
@Scope("prototype") //this changes bean scope from default "singleton" to "prototype"
public class Pet {

    private String name;
    private int age;
    private String species;

    //@Autowired //Field Injection - BAD! Breaks encapsulation
    private Owner owner; //This is a DEPENDENCY of Pet!

    /*boilerplate code---------------------------
      -no args, all args, getter/setter, toString */
    public Pet() {
    }

    public Pet(String name, int age, String species, Owner owner) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.owner = owner;
    }

    //We need a constructor will ONLY Owner for dependency injection
    @Autowired //Constructor Injection - good practice and my preferred way
    public Pet(Owner owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Owner getOwner() {
        return owner;
    }

    //@Autowired //Setter Injection - This is fine practice, injection will happen after instantiation
    //This is often used if a dependency is optional
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", species='" + species + '\'' +
                ", owner=" + owner +
                '}';
    }
}
