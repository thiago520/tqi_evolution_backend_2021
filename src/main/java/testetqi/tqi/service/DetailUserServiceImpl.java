package testetqi.tqi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import testetqi.tqi.data.DetailUserData;
import testetqi.tqi.model.UserModel;
import testetqi.tqi.repository.UserRepository;

import java.util.Optional;

@Component
public class DetailUserServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public DetailUserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> user = repository.findByEmail(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }

        return new DetailUserData(user);
    }
}
