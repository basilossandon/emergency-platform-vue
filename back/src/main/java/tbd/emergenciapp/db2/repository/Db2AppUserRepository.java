package tbd.emergenciapp.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.db2.model.Db2AppUser;


@Repository
public interface Db2AppUserRepository extends JpaRepository<Db2AppUser, Integer> {
    Db2AppUser findAppUserById(Integer id);
    Db2AppUser findAppUserByEmail(String email);
    @Query(value = "SELECT 2 AS key current_database() as database_name", nativeQuery = true)
    Db2AppUser getDb2AppUser();
}

