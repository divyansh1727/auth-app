package com.authapp.projectonauth.entities;

import java.security.Provider;
import java.time.Instant;
import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private String name;
    private String password;
    private boolean enable = true;
    private Instant createdAt=Instant.now();
    private Instant updatedAt=Instant.now();
    private Provider provider;






}
