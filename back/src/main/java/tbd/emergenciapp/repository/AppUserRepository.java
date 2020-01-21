package tbd.emergenciapp.repository;
import tbd.emergenciapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserById(Integer id);
    AppUser findAppUserByEmail(String email);
}

