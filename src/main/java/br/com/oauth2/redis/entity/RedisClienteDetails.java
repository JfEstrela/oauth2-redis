package br.com.oauth2.redis.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

import br.com.oauth2.redis.config.ClienteDetailServiceProperties;

@Component
public class RedisClienteDetails implements ClientDetails{

	private static final long serialVersionUID = -2368494355837747058L;
	
	@Autowired
    private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClienteDetailServiceProperties clienteDetailServiceProperties;

	@Override
	public String getClientId() {
		return clienteDetailServiceProperties.getClientId();
	}

	@Override
	public Set<String> getResourceIds() {
		return null;
	}

	@Override
	public boolean isSecretRequired() {
		return false;
	}

	@Override
	public String getClientSecret() {
		return passwordEncoder.encode(clienteDetailServiceProperties.getClientSecret());
	}

	@Override
	public boolean isScoped() {
		return false;
	}

	@Override
	public Set<String> getScope() {
		return new HashSet<>(Arrays.asList(new String [] {"read","write","trust"}));
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return  new HashSet<>(Arrays.asList(new String [] {"password","client_credentials", "refresh_token"}));
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {     
	    return new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority[] { new SimpleGrantedAuthority("ROLE_SERVICE")}));
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return clienteDetailServiceProperties.getTokenValiditySeconds();
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return clienteDetailServiceProperties.getTokenValiditySeconds();
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
	}

}
