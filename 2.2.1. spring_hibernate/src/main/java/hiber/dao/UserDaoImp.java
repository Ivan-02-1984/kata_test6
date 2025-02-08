package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().persist(user);
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().persist(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public User findVladelec(String car_name, String car_series){
      TypedQuery findVladelecQuery=sessionFactory.getCurrentSession()
              .createQuery("from Car where model=: model and  series=: series")
              .setParameter("model", car_name)
              .setParameter("series", car_series);
      List<Car> findCarList = findVladelecQuery.getResultList();
      if (!findCarList.isEmpty()) {
         Car findCar = findCarList.get(0);
         List<User> ListUser = listUsers();
         User FindUser = ListUser.stream()
                 .filter(user -> user.getCar().equals(findCar))
                 .findAny()
                 .orElse(null);
         return FindUser;
      }
      return null;
   }
}
