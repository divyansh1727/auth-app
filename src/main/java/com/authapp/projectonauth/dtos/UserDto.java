package com.authapp.projectonauth.dtos;

import com.authapp.projectonauth.entities.Provider;
import com.authapp.projectonauth.entities.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.time.Instant.now;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    private String email;
    private String name;
    private String password;
    private String image;
    private boolean enable = true;
    private Instant createdAt= now();
    private Instant updatedAt= now();
    private Provider provider=Provider.LOCAL;
    private Set<RoleDto> roles=new HashSet<>();
}
