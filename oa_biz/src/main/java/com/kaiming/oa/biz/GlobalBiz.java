package com.kaiming.oa.biz;

import com.kaiming.oa.entity.Employee;

public interface GlobalBiz {

    Employee login(String sn,String password);

    void changePassword(Employee employee);
}
