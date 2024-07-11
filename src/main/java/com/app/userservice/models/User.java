package com.app.userservice.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String name;
    private String hashedPassword;
    private String email;

    private boolean emailVerified= true;

    @ManyToMany
    private List<Role> roles ;

}
