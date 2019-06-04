package br.com.oauth2.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import br.com.oauth2.redis.entity.RedisClienteDetails;

@Service
public class RedisDetailsService implements ClientDetailsService{
	
	@Autowired
	private RedisClienteDetails redisClienteDetails;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {		
		return redisClienteDetails;
	}
}
