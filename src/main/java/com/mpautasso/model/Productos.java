package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue("producto")
public class Productos extends Prestacion {

}
