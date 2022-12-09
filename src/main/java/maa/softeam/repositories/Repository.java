package maa.softeam.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    void save(T t);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);
}
