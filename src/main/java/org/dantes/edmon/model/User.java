package org.dantes.edmon.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    private String email;

    private String firstName;

    private String secondName;

    private String password;

    private String city;

    private String country;
}
