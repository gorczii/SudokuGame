package logic;

/**
 * Created by jusia on 19.04.2017.
 */
public interface Dao<T> {
    public T read();
    public void write(T obj);
}
