package com.devsuperior.desafiocrudcliente.dto;

import com.devsuperior.desafiocrudcliente.entities.Client;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDTO {


    private Long id;

    @NotEmpty(message = "Name cant be empty.")
    private String name;

    private String cpf;

    private Double income;

    @PastOrPresent(message = "The date cant be a future one." )
    private LocalDate birthDate;

    private Integer children;


    public ClientDTO(Client entity) {
        id = entity.getId();
        name = entity.getName();
        cpf = entity.getCpf();
        income = entity.getIncome();
        birthDate = entity.getBirthDate();
        children = entity.getChildren();
    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }


    public Long getId() {
        return id;
    }

    public @NotEmpty(message = "Name cant be empty.") String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public @PastOrPresent(message = "The date cant be a future one.") LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
