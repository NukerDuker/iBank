package ru.skillfactory.ibankApi.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity(name="user_tab")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "balance")
    private Long balance;


}
