package br.com.zupacademy.felipe.gadelha.mercadolivre.infra.mock;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Category;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Profile;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.User;

@Component
@Transactional
@org.springframework.context.annotation.Profile({"dev"})
public class Mock implements ApplicationRunner{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		manager.createQuery("delete from User").executeUpdate();
		manager.createNativeQuery("alter table users AUTO_INCREMENT = 1").executeUpdate();
		manager.createQuery("delete from Profile").executeUpdate();
		manager.createNativeQuery("alter table profiles AUTO_INCREMENT = 1").executeUpdate();
		manager.createQuery("delete from Category").executeUpdate();
		manager.createNativeQuery("alter table categories AUTO_INCREMENT = 1").executeUpdate();
		manager.createQuery("delete from Product").executeUpdate();
		manager.createNativeQuery("alter table products AUTO_INCREMENT = 1").executeUpdate();

		var profileUser = new Profile("ROLE_USER");
		var profileAdmin = new Profile("ROLE_ADMIN");
		
		manager.persist(profileUser);
		manager.persist(profileAdmin);
		String encode = new BCryptPasswordEncoder().encode("123456");
		var user = new User("user@email.com", encode, List.of(profileUser));
		var admin = new User("admin@email.com", encode, List.of(profileAdmin));
		manager.persist(user);
		manager.persist(admin);
		
		var home = new Category("Home", null);
		var tech = new Category("Tech", null);
		manager.persist(home);
		manager.persist(tech);
	}

}
