package maa.softeam.repositories;

import maa.softeam.entities.Costumer;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CostumerRepository implements Repository<Costumer, Integer> {
    public final ConcurrentHashMap<Integer, SoftReference<Costumer>> cache = new ConcurrentHashMap<>();

    @Override
    public void save(Costumer costumer) {
        SoftReference<Costumer> softReference = new SoftReference<>(costumer);
        this.cache.put(costumer.getId(), softReference);
    }

    @Override
    public Optional<Costumer> findById(Integer id) {
        return this.cache.containsKey(id) ? Optional.of(this.cache.get(id).get()) : Optional.empty();
    }

    @Override
    public List<Costumer> findAll() {
        return this.cache.values().stream().map(SoftReference::get).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        this.cache.remove(id);
    }
}
