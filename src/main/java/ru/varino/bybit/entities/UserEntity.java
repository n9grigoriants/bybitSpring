package ru.varino.bybit.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("tg_id")
    @Column(name = "tg_id")
    private Long tgId;

    @Column(length = 200)
    private String username;

    public UserEntity() {}

}