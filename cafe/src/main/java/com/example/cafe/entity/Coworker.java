package com.example.cafe.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class Coworker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Поле должно быть от 2 до 30 символов")
    @Column(name = "fio")
    private String fio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_position")
    private Position position;

    @OneToMany(mappedBy = "coworker")
    private List<Order> orderList;

    public Coworker(String fio, Position position) {
        this.fio = fio;
        this.position = position;
    }

    public Coworker() {
    }
}
