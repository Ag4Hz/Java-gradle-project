package edu.itplus.bibliospring.backend;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import edu.itplus.bibliospring.backend.services.LoginService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    @Autowired
    private LoginService loginService;

    @PostConstruct
    public void postConstruct() {
        User user = new User();
        user.setUsername("Sanyi");
        user.setPassword("asd");
        //loginService.register(user);
        System.out.println(loginService.login(user));

        User u1 = new User();
        u1.setUsername("Pistike2");
        u1.setPassword("asd");
        //loginService.register(u1);
        u1.setPassword("asd");
        System.out.println(loginService.login(u1));

        /*
        DAOFactory factory = DAOFactory.getInstance();

        UserDAO userDAO = factory.getUserDAO();

        User u1 = new User();
        u1.setUsername("Pisti");
        u1.setPassword("asd");

        User u2 = new User();
        u2.setUsername("Isti");
        u2.setPassword("dddddddd");

        User u3 = new User();
        u3.setUsername("Elemér");
        u3.setPassword("dddddddd");

        System.out.println("=== Creating users ===");
        userDAO.create(u1);
        userDAO.create(u2);
        //userDAO.create(u3);
        System.out.println("=== Creating users done! ===");

        System.out.println("\n=== Finding users by username ===");
        System.out.println("Found: " + userDAO.findByUsername("Pisti"));
        System.out.println("Found: " + userDAO.findByUsername("Isti"));

        System.out.println("\n=== Listing all users ===");
        List<User> users = userDAO.getAll();
        users.forEach(System.out::println);

        System.out.println("\n=== Getting user 'Pisti' ===");
        System.out.println("Get Pisti: " + userDAO.findByUsername("Pisti"));

        System.out.println("\n=== Deleting user 'Isti' ===");
        u2 = userDAO.findByUsername(u2.getUsername());
        userDAO.delete(u2);

        System.out.println("\n=== Updating user 'Pisti' password ===");
        u1 = userDAO.findByUsername("Pisti");
        u1.setPassword("asd123");
        userDAO.update(u1);

        System.out.println("\n=== Listing all users after update and delete ===");
        users = userDAO.getAll();
        users.forEach(System.out::println);

        System.out.println("\n=== Deleting user 'Pisti' ===");
        userDAO.delete(u1);

        System.out.println("\n=== Final user list ===");
        users = userDAO.getAll();
        users.forEach(System.out::println);
         */
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}