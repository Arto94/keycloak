package com.example.demo.model;

import com.example.demo.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.keycloak.representations.AccessToken;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@Table(name = "admin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Transient
    Set<String> roles;


    public User(AccessToken accessToken) {
        name = accessToken.getPreferredUsername();
        email = accessToken.getEmail();
        AccessToken.Access realmAccess = accessToken.getRealmAccess();
        roles = realmAccess.getRoles();
    }

}
