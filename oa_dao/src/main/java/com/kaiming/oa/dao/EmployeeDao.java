package com.kaiming.oa.dao;

import com.kaiming.oa.entity.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.kaiming.oa.entity.Employee;

@Repository("employeeDao")
@Primary
public interface EmployeeDao {

    void insert(Employee employee);

    void update(Employee employee);

    void delete(String sn);

    Employee select(String sn);

    List<Employee> selectAll();

    List<Employee> selectByDepartmentAndPost(@Param("dsn")String dsn, @Param("post") String post);
}
