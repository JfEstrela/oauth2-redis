package br.com.oauth2.redis.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.oauth2.redis.entity.User;
import br.com.oauth2.redis.repository.ClientDetailsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private ClientDetailsRepository clientDetailsRepository;
	
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @PostConstruct
    private void createUserAdm() {
    	User user = new User();
    	user.setUsername("oauth2-redis");
    	user.setPassword(passwordEncoder.encode("sider-2htuao"));
    	user.setGrantedAuthority("ROLE_USER,ROLE_ADMIN");
    	user.setAccountNonExpired(true);
    	user.setAccountNonLocked(true);
    	user.setCredentialsNonExpired(true);
    	user.setEnabled(true);
    	clientDetailsRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserDetails(username);
    }

    private UserDetails getUserDetails(String username) {
      User user = this.clientDetailsRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("Invalid username or password."));
      return  user.passwordEncoder(passwordEncoder::encode);
    }

}