/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.ng.Controller;

import com.portfolio.ng.Dto.dtoEducacion;
import com.portfolio.ng.Entity.Educacion;
import com.portfolio.ng.Security.Controller.Mensaje;
import com.portfolio.ng.Service.ServiceEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://frontendgraff.web.app")
@RestController
@RequestMapping("/educacion")

public class CEducacion {
     @Autowired
     ServiceEducacion serviceEducacion;
     
  @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = serviceEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
        
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!serviceEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Educacion educacion = serviceEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
     @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("El Id no existe"), HttpStatus.NOT_FOUND);
        }
        serviceEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Estudio eliminado"), HttpStatus.OK);
    }
         
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoeducacion){      
        if(StringUtils.isBlank(dtoeducacion.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(serviceEducacion.existsByNombreE(dtoeducacion.getNombreE()))
            return new ResponseEntity(new Mensaje("Esos estudios ya existen"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion(dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE());
        serviceEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educación agregada"), HttpStatus.OK);
    }
        @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoeducacion){
        //Validamos si existe el ID
        if(!serviceEducacion.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombre de estudios
        if(serviceEducacion.existsByNombreE(dtoeducacion.getNombreE()) && serviceEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa educación ya existe"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio
        if(StringUtils.isBlank(dtoeducacion.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = serviceEducacion.getOne(id).get();
        educacion.setNombreE(dtoeducacion.getNombreE());
        educacion.setDescripcionE((dtoeducacion.getDescripcionE()));
        
        serviceEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educación actualizada"), HttpStatus.OK);
     
    }
 
    
    
    
     
     
     
     
}
