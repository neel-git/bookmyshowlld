package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity (name = "users")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
    private boolean isSignedIn = false;
}
