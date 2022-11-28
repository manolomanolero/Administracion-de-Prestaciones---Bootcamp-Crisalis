package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
@DiscriminatorValue("null")
public class ConsumidorFinal extends Clientes{
}
