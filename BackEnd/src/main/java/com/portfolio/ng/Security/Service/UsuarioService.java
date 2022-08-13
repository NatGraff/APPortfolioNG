/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.ng.Security.Service;

import com.portfolio.ng.Security.Entity.Usuario;
import com.portfolio.ng.Security.Repository.iUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    iUsuarioRepository iusuarioRepository;
     public Optional<Usuario> getByUsuario(String nombreUsuario){
         return iusuarioRepository.findByNombreUsuario(nombreUsuario);
     }
            
    public boolean existsByNombreUsuario(String nombreUsuario){
        return iusuarioRepository.existsByNombreUsuario (nombreUsuario);
        
    }
    public boolean existsByEmail(String email){
        return iusuarioRepository.existsByEmail (email);
    }
    
    public void save (Usuario usuario){
        iusuarioRepository.save(usuario);
    }

    Object getByNombreUsuario(String nombreUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean existsByUsuario(String nombreUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    }
