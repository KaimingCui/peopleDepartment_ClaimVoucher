package com.kaiming.oa.dao;

import com.kaiming.oa.entity.ClaimVoucherItem;
import com.kaiming.oa.entity.Department;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("claimVoucherItemDao")
@Primary
public interface ClaimVoucherItemDao {
    void insert(ClaimVoucherItem claimVoucherItem);
    void update(ClaimVoucherItem claimVoucherItem);
    void delete(int id);
    List<ClaimVoucherItem> selectByClaimVoucher(int cvid);
}
