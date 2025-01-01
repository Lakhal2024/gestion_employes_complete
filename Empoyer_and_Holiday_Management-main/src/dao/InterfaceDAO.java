package dao;

import java.util.List;

public interface InterfaceDAO<T> {
    boolean add(T entity);
    boolean update(T entity);
    boolean delete(int id);
    List<T> getAll();
    String getPassword(String username);
    String getRole(String username);
    int getHolidayDays(int id);
    boolean createLogin(int employee_id, String username, String password);
}
