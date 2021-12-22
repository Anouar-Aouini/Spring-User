package com.fivepoints.spring;

import com.fivepoints.spring.entities.*;
import com.fivepoints.spring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
public class Application implements ApplicationRunner {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// this bean used to crypt the password
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoderBean = applicationContext.getBean(BCryptPasswordEncoder.class);
		return passwordEncoderBean;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Clean up database tables
//		this.roleRepository.deleteAllInBatch();
//		this.userRepository.deleteAllInBatch();
//		this.userDetailsRepository.deleteAllInBatch();
//		this.postRepository.deleteAllInBatch();
//		this.tagRepository.deleteAllInBatch();
//		this.commentRepository.deleteAllInBatch();

		// Save roles
		Role superAdminRole = this.roleRepository.save(new Role(ERole.SUPER_ADMIN));
		Role adminRole = this.roleRepository.save(new Role(ERole.ADMIN));
		Role userRole = this.roleRepository.save(new Role(ERole.USER));
		Role guestRole = this.roleRepository.save(new Role(ERole.GUEST));



		// Save users
		User user1 = new User("hatem", "dagbouj",
					"hatem.dagbouj@fivepoints.fr",
				this.passwordEncoder().encode("123456789"));

		// Save users details
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 7, 21);
		UserDetails userDetails1 = new UserDetails(30, "+91-8197882053", Gender.MALE, dateOfBirth.getTime(),
				"747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore",
				"Karnataka", "India", "560008", new Date("11/11/1994"),
				"https://github.com/dagboujhatem", "https://www.linkedin.com/in/dagbouj-hatem");

		// Affect user1 to userDetails1
		user1.setDetails(userDetails1); // Set child reference
		userDetails1.setUser(user1); // Set parent reference
		this.userRepository.save(user1);

		// Save Posts
		Post post1 = new Post("Spring REST API","", true);
		Post post2 = new Post("Node JS REST endpoints","", false);
		Post post3 = new Post("Angular vs React","", true);

		// Create two tags
		Tag tag1 = new Tag("Spring Boot");
		Tag tag2 = new Tag("Hibernate");


		// Add tag references in the post
		post1.getTags().add(tag1);
		post1.getTags().add(tag2);
		// Add post reference in the tags
		tag1.getPosts().add(post1);
		tag2.getPosts().add(post1);

		// Add Comments to Posts
		Comment comment0 = new Comment("This is my comment.");
		Comment comment1 = new Comment("This is my first comment.");
		Comment comment2 = new Comment("This is my second comment.");
		Comment comment3 = new Comment("This is my third comment.");

		// associate user1 to posts
		post1.setUser(user1);
		post2.setUser(user1);
		post3.setUser(user1);
		this.postRepository.save(post1);
		this.postRepository.save(post2);
		this.postRepository.save(post3);
		this.userRepository.save(user1);

		// ManyToMany Relations
		Set<Role> roles = new HashSet<>();
		roles.add(superAdminRole);
		roles.add(adminRole);
		roles.add(userRole);
		roles.add(guestRole);
		user1.setRoles(roles);
		this.userRepository.save(user1);


	}
}
