/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitec.dclx;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ian
 */
//Representational State Transfer
@RestController
@RequestMapping("/api")
public class ControladorPerfil {

    //inversion de control o inyeccion de dependecia
    @Autowired
    RepoPerfil repoPerfil;

    @GetMapping("/hola")
    public Saludo saludar() {

        Saludo s = new Saludo();
        s.setNombre("Ian");
        s.setMensaje("Mi primer mensaje API REST");
        return s;

    }

    //Metodo para guardar en un backend los datos del perfil - POST
    @PostMapping("/perfil")
    public Estatus guardar(@RequestBody String json) throws Exception {

        //Des-serializacion
        ObjectMapper mapper = new ObjectMapper();
        Perfil perfil = mapper.readValue(json, Perfil.class);

        //Verificacion del objeto
        System.out.println("Perfil leido " + perfil);

        //TODO Guardar perfil en base de datos MongoDB
        repoPerfil.save(perfil);
        //Estatus
        Estatus e = new Estatus();
        e.setSuccess(true);
        e.setMensaje("Perfil guardado con exito");
        return e;
    }

    @PutMapping("/perfil")
    public Estatus actualizar(@RequestBody String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Perfil perfil = mapper.readValue(json, Perfil.class);

        //Verificacion del objeto
        System.out.println("Perfil leido " + perfil);

        //TODO Guardar perfil en base de datos MongoDB
        repoPerfil.save(perfil);
        //Estatus
        Estatus e = new Estatus();
        e.setSuccess(true);
        e.setMensaje("Perfil actualizado con exito");
        return e;

    }

    @DeleteMapping("/perfil/{id}")
    public Estatus borrar(@PathVariable String id) {
        //invocamos el repositorio
        repoPerfil.deleteById(id);
        //Genereamos el Estatus
        Estatus e = new Estatus();
        e.setMensaje("Perfil borrado con exito");
        e.setSuccess(true);
        return e;

    }

    @GetMapping("/perfil")
    public List<Perfil> buscarTodos() {

        return repoPerfil.findAll();
        
    }

    @GetMapping("/perfil/{id}")
    public Perfil buscarPorId(@PathVariable String id) {
        return repoPerfil.findById(id).get();
    }

}
