package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Name1", "LastName1", "address1@google.com");
      User user2 = new User("Name2", "LastName2", "address2@google.com");
      User user3 = new User("Name3", "LastName3", "address3@google.com");
      User user4 = new User("Name4", "LastName4", "address4@google.com");

      Car car1 = new Car("Car1", 1001);
      Car car2 = new Car("Car2", 1002);
      Car car3 = new Car("Car3", 1003);
      Car car4 = new Car("Car4", 1004);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      // 1. Пользователи с машинами
      System.out.println("Пользователи с машинами:");
      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println("Выбрать пользователя, владеющего машиной по ее модели и серии:");
      System.out.println(userService.getUserByCar("Car1", 1001));

      System.out.println("Нет пользователя с такой машиной:");
        try {
           User notFoundUser = userService.getUserByCar("Car5", 50);
      } catch (NoResultException e) {
           System.out.println("User not found");

      }

      context.close();
   }
}