package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        userService.add(new User("Maria", "Mishneva", "maria@mail.ru",
                new Car("creta", 22222)));
        userService.add(new User("Ivan", "Ivanov", "ivan@mail.ru",
                new Car("FX35", 77777)));
       userService.add(new User("Nikita", "Frolov", "nikita@mail.ru",
                new Car("vesta", 12345)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("CarId = " + user.getCar().getId());
            System.out.println("Model = " + user.getCar().getModel());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();
        }

        User user = userService.getUserFromCar("creta", 22222);
        System.out.println("Владелец авто: " + user.getFirstName() + " " + user.getLastName());

        context.close();
    }
}
