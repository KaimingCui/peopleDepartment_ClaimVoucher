package com.kaiming.oa.biz;

import com.kaiming.oa.entity.ClaimVoucher;
import com.kaiming.oa.entity.ClaimVoucherItem;
import com.kaiming.oa.entity.DealRecord;

import java.util.List;

public interface ClaimVoucherBiz {

    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher get(int id);

    List<ClaimVoucherItem> getItems(int cvid);

    List<DealRecord> getRecords(int cvid);

    List<ClaimVoucher> getForSelf(String sn);

    List<ClaimVoucher> getForDeal(String sn);

    void update(ClaimVoucher claimVoucher,List<ClaimVoucherItem> items);

    void submit(int id);

    void deal(DealRecord dealRecord);
}
