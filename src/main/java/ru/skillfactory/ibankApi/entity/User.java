package ru.skillfactory.ibankApi.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Data
@Table(name="user_tab")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "balance")
    private Long balance;


}
