package com.portfolio.ng.Interface;

import com.portfolio.ng.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    //Traer Lista de Personas
   public List<Persona> getPersona(); 
   
   //Guardar objeto de tipo Persona
   public void savePersona(Persona persona);
   
   //Eliminar un usuario Persona busqueda desde ID
   public void deletePersona(Long id);
   
   //Busqueda de persona
   public Persona findPersona(Long id);
}
