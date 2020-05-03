package com.kaiming.oa.dao;

import com.kaiming.oa.entity.DealRecord;
import com.kaiming.oa.entity.Department;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("dealRecordDao")
@Primary
public interface DealRecordDao {
    void insert(DealRecord dealRecord);
    List<DealRecord> selectByClaimVoucher(int cvid);
}
