package tbd.emergenciapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.model.Dimension;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Integer> {
    Dimension findDimensionById(Integer id);
}
