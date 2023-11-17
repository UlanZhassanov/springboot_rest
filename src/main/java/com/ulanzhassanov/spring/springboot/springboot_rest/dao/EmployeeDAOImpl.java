package com.ulanzhassanov.spring.springboot.springboot_rest.dao;

import com.ulanzhassanov.spring.springboot.springboot_rest.entity.Employee;
//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
//        Session session = entityManager.unwrap(Session.class);
//
//        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();

        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
//        Session session = entityManager.unwrap(Session.class);
//
//        session.saveOrUpdate(employee);

        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
    }

    @Override
    public Employee getEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//
//        Employee employee = session.get(Employee.class, id);

        Employee employee = entityManager.find(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//      variant 1
//      Session session = sessionFactory.getCurrentSession();
//      Query <Employee> query = session.createQuery("delete from Employee where id = :employeeId");
//      query.setParameter("employeeId", id);
//      query.executeUpdate();

//      using stream API
//        entityManager.unwrap(Session.class)
//                .createQuery("delete from Employee where id = :employeeId")
//                .setParameter("employeeId", id)
//                .executeUpdate();

        entityManager.createQuery("delete from Employee where id = :employeeId")
                .setParameter("employeeId", id)
                .executeUpdate();
    }
}
