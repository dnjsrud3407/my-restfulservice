package kr.co.wonkyung.myrestfulservice.repository;

import jakarta.persistence.EntityManager;
import kr.co.wonkyung.myrestfulservice.bean.User;
import kr.co.wonkyung.myrestfulservice.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public List<User> findAll(int page, int size) {
        return em.createQuery(
                "select u from User u", User.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    public void deleteUser(User user) {
        em.remove(user);
    }

    public void save(User user) {
        em.persist(user);
    }
}
