package wp.finki.ukim.mk.labb.repository.impl;

import org.springframework.stereotype.Repository;
import wp.finki.ukim.mk.labb.bootstrap.DataHolder;
import wp.finki.ukim.mk.labb.model.BookStore;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookStoreRepository {
    public List<BookStore> findAll(){
        return DataHolder.bookStores;
    }

    public Optional<BookStore> findByID(String id){
        return DataHolder.bookStores.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

}
