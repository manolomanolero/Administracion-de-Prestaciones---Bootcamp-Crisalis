package com.mpautasso.util;

import com.mpautasso.model.*;
import com.mpautasso.repository.EmpresaRepository;
import com.mpautasso.repository.ImpuestosRepository;
import com.mpautasso.repository.PrestacionesRepository;
import com.mpautasso.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PrestacionesRepository prestacionesRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ImpuestosRepository impuestosRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            List<Rol> roleList = rolRepository.findAll();

            if (roleList.size() < 2) {

                Rol role1 = new Rol(1, "ROLE_USER");
                rolRepository.save(role1);

                Rol role2 = new Rol(2, "ROLE_ADMIN");
                rolRepository.save(role2);

            }

            List<Prestacion> prestaciones = prestacionesRepository.findAll();

            if(prestaciones.size() < 2){
                Prestacion prestacion1 = new Producto(1L, "fideos", 160d);
                prestacionesRepository.save(prestacion1);
                Prestacion prestacion2 = new Servicio(2L, "internet", 2300d);
                prestacionesRepository.save(prestacion2);
                Prestacion prestacion3 = new Producto(3L, "carne", 999d);
                prestacionesRepository.save(prestacion3);
                Prestacion prestacion4 = new Servicio(4L, "agua", 1100d);
                prestacionesRepository.save(prestacion4);

            }

            List<Impuesto> impuestos = impuestosRepository.findAll();

            if(impuestos.size() < 2){
                Impuesto impuesto1 = new Impuesto(1L, "IVA", 21d);
                impuestosRepository.save(impuesto1);
                Impuesto impuesto2 = new Impuesto(2L, "Ingresos Brutos", 5d);
                impuestosRepository.save(impuesto2);
                Impuesto impuesto3 = new Impuesto(3L, "Impuesto pais", 20d);
                impuestosRepository.save(impuesto3);
                Impuesto impuesto4 = new Impuesto(4L, "Bienes personales", 15d);
                impuestosRepository.save(impuesto4);
            }


            List<Empresa> empresas = empresaRepository.findAll();

            if(empresas.size() < 2) {
                Empresa empresa1 = new Empresa(1L, 400003321209L, "Marolio");
                empresaRepository.save(empresa1);
                Empresa empresa2 = new Empresa(2L, 403349982129L, "Coca-Cola");
                empresaRepository.save(empresa2);
                Empresa empresa3 = new Empresa(3L, 489390982129L, "EdeSur");
                empresaRepository.save(empresa3);
                Empresa empresa4 = new Empresa(4L, 433442564129L, "Playadito");
                empresaRepository.save(empresa4);
            }



        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
