package com.sidorov.igor.spring.rest.DAO;

import com.sidorov.igor.spring.rest.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Employee", Employee.class).getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        return sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Query<Employee> query = sessionFactory.getCurrentSession()
                .createQuery("delete from Employee " + "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
