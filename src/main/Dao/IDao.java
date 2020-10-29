package main.Dao;

import java.util.List;

public interface IDao <T> {
    List<T> select();
    boolean insert(T item);
    boolean delete(int id);
    T get(int id);
}
