package com.kaiming.oa.biz;

import com.kaiming.oa.entity.Department;
import com.kaiming.oa.entity.Employee;
import java.util.List;


public interface EmployeeBiz {

    void add(Employee employee);
    void edit(Employee employee);
    void remove(String sn);
    Employee get(String sn);
    List<Employee> getAll();
}
