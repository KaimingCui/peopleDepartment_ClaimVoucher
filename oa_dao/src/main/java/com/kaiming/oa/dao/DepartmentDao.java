package com.kaiming.oa.dao;

import com.kaiming.oa.entity.Department;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
@Primary
public interface DepartmentDao {

    void insert(Department department);

    void update(Department department);

    void delete(String sn);

    Department select(String sn);

    List<Department> selectAll();
}
