package br.com.zupacademy.felipe.gadelha.mercadolivre.infra.mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Ask;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Category;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Opinion;
import br.com.zupacademy.felipe.gadelha.mercadolivre.domain.entity.Product;
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
//		manager.createQuery("delete from User").executeUpdate();
//		manager.createNativeQuery("alter table users AUTO_INCREMENT = 1").executeUpdate();
//		manager.createQuery("delete from Profile").executeUpdate();
//		manager.createNativeQuery("alter table profiles AUTO_INCREMENT = 1").executeUpdate();
//		manager.createQuery("delete from Category").executeUpdate();
//		manager.createNativeQuery("alter table categories AUTO_INCREMENT = 1").executeUpdate();
//		manager.createQuery("delete from Product").executeUpdate();
//		manager.createNativeQuery("alter table products AUTO_INCREMENT = 1").executeUpdate();

		var profileUser = new Profile("ROLE_USER");
		var profileAdmin = new Profile("ROLE_ADMIN");
		
		manager.persist(profileUser);
		manager.persist(profileAdmin);
		String encode = new BCryptPasswordEncoder().encode("123456");
		var user = new User("user@email.com", encode, List.of(profileUser));
		var user1 = new User("user1@email.com", encode, List.of(profileUser));
		var user2 = new User("user2@email.com", encode, List.of(profileUser));
		var user3 = new User("user3@email.com", encode, List.of(profileUser));
		var admin = new User("admin@email.com", encode, List.of(profileAdmin));
		manager.persist(user);
		manager.persist(user1);
		manager.persist(user2);
		manager.persist(user3);
		manager.persist(admin);
		
		var home = new Category("Home", null);
		var tech = new Category("Tech", null);
		manager.persist(home);
		manager.persist(tech);
		
		var smartphone = Product.builder()
			.name("Samsung S21")
			.description("Smartphone top da samsung")
			.price(new BigDecimal(4000))
			.availableQuantity(10)
			.owner(admin)
			.category(tech)
			.features(Map.of(
					"Memória interna", "256GB", 
					"com NFC", "Não", 
					"Câmera traseira principal", "13px")
					).build();
		manager.persist(smartphone);
		
		var opinion = new Opinion(3, "Muito bom o produto", "pena que não vem com NFC", user, smartphone);
		var opinion2 = new Opinion(3, "Muito ruim o produto", "não vem com NFC", user3, smartphone);
		manager.persist(opinion);
		manager.persist(opinion2);
		
		var ask = new Ask("Qual o nome do processador?", user, smartphone);
		var ask1 = new Ask("Qual a quantidade de memória?", user, smartphone);
		var ask2 = new Ask("É Dual-chip?", user1, smartphone);
		var ask3 = new Ask("É importado?", user, smartphone);
		var ask4 = new Ask("Possui desconto?", user2, smartphone);
		var ask5 = new Ask("Da pra pagar com pix?", user, smartphone);
		var ask6 = new Ask("Qual o prazo de entrega?", user3, smartphone);
		var ask7 = new Ask("Vem com capinha?", user, smartphone);
		manager.persist(ask);
		manager.persist(ask1);
		manager.persist(ask2);
		manager.persist(ask3);
		manager.persist(ask4);
		manager.persist(ask5);
		manager.persist(ask6);
		manager.persist(ask7);
	}

}
