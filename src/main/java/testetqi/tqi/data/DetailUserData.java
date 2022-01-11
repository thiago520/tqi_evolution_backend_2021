package testetqi.tqi.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import testetqi.tqi.model.UserModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetailUserData implements UserDetails {

    private final Optional<UserModel> user;

    public DetailUserData(Optional<UserModel> user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.orElse(new UserModel()).getPassword();
    }

    @Override
    public String getUsername() {
        return user.orElse(new UserModel()).getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
