package com.kaiming.oa.dao;

import com.kaiming.oa.entity.ClaimVoucher;
import com.kaiming.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherDao")
@Primary
public interface ClaimVoucherDao {
    void insert(ClaimVoucher claimVoucher);
    void update(ClaimVoucher claimVoucher);
    void delete(int id);
    ClaimVoucher select(int id);
    List<ClaimVoucher> selectByCreateSn(String csn);
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}
