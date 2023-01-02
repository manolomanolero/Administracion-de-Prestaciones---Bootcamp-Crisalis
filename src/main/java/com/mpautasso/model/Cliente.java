package com.mpautasso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("case when empresa_id is not null then 1 else 2 end")
public abstract class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dni;

    private String nombre;
    private String apellido;

    @ManyToOne(fetch = FetchType.EAGER,cascade= CascadeType.MERGE)
    @JoinColumn( name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    public Cliente(Long dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, Long dni, String nombre, String apellido) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long dni, String nombre, String apellido, Empresa empresa) {
        this(dni, nombre, apellido);
        this.empresa = empresa;
    }

    public abstract String getType();



    /*
    List<Servicios> serviciosContratados;
     */
}
