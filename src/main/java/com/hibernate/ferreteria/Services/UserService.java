package com.hibernate.ferreteria.Services;

import com.hibernate.ferreteria.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// UserDetailsService es interfaz de Spring Security que exige implementar loadUserByUserName();
public class UserService implements UserDetailsService {

    @Autowired
    // Inyecta repositorio para consultar usuarios en la base de datos
    private UserRepository userRepository;

    // Spring security llama a este metodo automaticamente al autenticar un usuario
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Busca el usuario en BD; si no existe lanza excepcion
        var user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + userName));

        // Retorna UserDetails con; username, password y list de roles
        // Spring security usa este objeto para validar credenciales y permisos.
        return new User(user.getUsername(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRol())));
    }
}
