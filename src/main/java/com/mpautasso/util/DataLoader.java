package com.mpautasso.util;

import com.mpautasso.model.*;
import com.mpautasso.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    @Autowired
    private ClientesRepository clientesRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private DetallesPedidoRepository detallesRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            List<Rol> roleList = rolRepository.findAll();

            if (roleList.size() < 2) {

                Rol role1 = rolRepository.save(new Rol(1, "ROLE_USER"));
                Rol role2 = rolRepository.save(new Rol(2, "ROLE_ADMIN"));

            }

            List<Prestacion> prestaciones = prestacionesRepository.findAll();

            if(prestaciones.size() < 5){
                Prestacion prestacion1 = prestacionesRepository.save(new Producto(1L, "fideos", 160d));
                Prestacion prestacion2 = prestacionesRepository.save(new Servicio(2L, "internet", 2300d));
                Prestacion prestacion3 = prestacionesRepository.save(new Producto(3L, "carne", 999d));
                Prestacion prestacion4 = prestacionesRepository.save(new Servicio(4L, "agua", 1100d));
                Prestacion prestacion5 = prestacionesRepository.save(new Producto(5L, "pollo", 1110d));
                Prestacion prestacion6 = prestacionesRepository.save(new Servicio(6L, "luz", 1500d));
                Prestacion prestacion7 = prestacionesRepository.save(new Producto(7L, "aceite", 458d));
                Prestacion prestacion8 = prestacionesRepository.save(new Servicio(8L, "cable", 1100d));
            }

            List<Impuesto> impuestos = impuestosRepository.findAll();

            if(impuestos.size() < 2){
                Impuesto impuesto1 = impuestosRepository.save(new Impuesto(1L, "IVA", 21d));
                Impuesto impuesto2 = impuestosRepository.save(new Impuesto(2L, "Ingresos Brutos", 5d));
                Impuesto impuesto3 = impuestosRepository.save(new Impuesto(3L, "Impuesto pais", 20d));
                Impuesto impuesto4 = impuestosRepository.save(new Impuesto(4L, "Bienes personales", 15d));
            }


            List<Empresa> empresas = empresaRepository.findAll();
            List<Cliente> clientes = clientesRepository.findAll();

            if(empresas.size() < 2 || clientes.size() < 3) {
                Empresa empresa1 = empresaRepository.save(new Empresa(1L, 400003321209L, "Marolio", new Date()));
                Empresa empresa2 = empresaRepository.save(new Empresa(2L, 403349982129L, "Coca-Cola", new Date()));
                Empresa empresa3 = empresaRepository.save(new Empresa(3L, 489390982129L, "EdeSur", new Date()));
                Empresa empresa4 = empresaRepository.save(new Empresa(4L, 433442564129L, "Playadito", new Date()));


                Cliente consumidor1 = clientesRepository.save(
                        new ConsumidorFinal(1L, 22222222L, "Marcos", "Perez")
                );
                Cliente consumidor2 = clientesRepository.save(
                        new ConsumidorFinal(2L, 24442222L, "Maria", "Martinez")
                );
                Cliente consumidor3 = clientesRepository.save(
                        new ConsumidorFinal(3L, 44442222L, "Martin", "Perez")
                );

                Cliente representante1 = clientesRepository.save(
                        new RepresentanteEmpresa(4L, 44442222L, "Florencia", "Paz", empresa1)
                );
                Cliente representante2 = clientesRepository.save(
                        new RepresentanteEmpresa(5L, 35253221L, "Carlos", "Paz", empresa2)
                );
                Cliente representante3 = clientesRepository.save(
                        new RepresentanteEmpresa(6L, 42185312L, "Facundo", "Vidal", empresa4)
                );
            }

            List<Pedido> pedidos = pedidoRepository.findAll();

            if(pedidos.size() < 3){
                Impuesto impuesto1 = impuestosRepository.findById(1L).get();
                Impuesto impuesto2 = impuestosRepository.findById(2L).get();
                List<Impuesto> impuestos1 = new ArrayList<>();
                impuestos1.add(impuesto1);
                impuestos1.add(impuesto2);


                DetallesPedido detalles1 = new DetallesPedido(null, 1,
                        prestacionesRepository.findById(1L).get(), impuestos1,0);
                DetallesPedido detalles2 = new DetallesPedido(null, 1,
                        prestacionesRepository.findById(2L).get(), impuestos1,0);
                DetallesPedido detalles3 = new DetallesPedido(null, 1,
                        prestacionesRepository.findById(3L).get(), impuestos1,0);
                DetallesPedido detalles4 = new DetallesPedido(null, 1,
                        prestacionesRepository.findById(4L).get(), impuestos1,0);


                Pedido pedido1 = pedidoRepository.save(new Pedido(
                                    clientesRepository.findById(1L).get(),
                                    new ArrayList<>(List.of(detalles1))
                                ));
                Pedido pedido2 = pedidoRepository.save(new Pedido(
                        clientesRepository.findById(2L).get(),
                        new ArrayList<>(Arrays.asList(detalles2, detalles4))
                ));

                Pedido pedido3 = pedidoRepository.save(new Pedido(
                        clientesRepository.findById(3L).get(),
                        new ArrayList<>(List.of(detalles3))
                ));
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
