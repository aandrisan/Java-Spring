package ro.sd.a2.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Actor;
import ro.sd.a2.entity.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findAllById (String id);
}
