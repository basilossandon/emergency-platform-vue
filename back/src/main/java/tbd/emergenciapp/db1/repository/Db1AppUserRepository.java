package tbd.emergenciapp.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tbd.emergenciapp.db1.model.Db1AppUser;


public interface Db1AppUserRepository extends JpaRepository<Db1AppUser, Integer> {
    Db1AppUser findAppUserById(Integer id);
    Db1AppUser findAppUserByEmail(String email);

    @Query(value = "SELECT 1 AS key current_database() as database_name", nativeQuery = true)
    Db1AppUser getDb2AppUser();
}
