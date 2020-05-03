package com.kaiming.oa.biz.impl;

import com.kaiming.oa.biz.ClaimVoucherBiz;
import com.kaiming.oa.dao.ClaimVoucherDao;
import com.kaiming.oa.dao.ClaimVoucherItemDao;
import com.kaiming.oa.dao.EmployeeDao;
import com.kaiming.oa.entity.ClaimVoucher;
import com.kaiming.oa.entity.ClaimVoucherItem;
import com.kaiming.oa.entity.DealRecord;
import com.kaiming.oa.entity.Employee;
import com.kaiming.oa.global.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaiming.oa.dao.DealRecordDao;

import java.util.Date;
import java.util.List;

@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {

    @Autowired
    ClaimVoucherDao claimVoucherDao;

    @Autowired
    ClaimVoucherItemDao claimVoucherItemDao;

    @Autowired
    DealRecordDao dealRecordDao;

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.insert(claimVoucher);

        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDao.insert(item);
        }
    }

    @Override
    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    @Override
    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    @Override
    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucher(cvid);
    }

    @Override
    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    @Override
    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByNextDealSn(sn);
    }

    @Override
    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_CREATED);
        claimVoucherDao.update(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        for(ClaimVoucherItem old:olds){
            boolean isHave = false;

            for(ClaimVoucherItem item:items){
                if(old.getId().equals(item.getId())){
                    isHave = true;
                    break;
                }
            }

            if(!isHave){
                claimVoucherItemDao.delete(old.getId());
            }
        }

        for(ClaimVoucherItem item:items){
            item.setClaimVoucherId(claimVoucher.getId());
            if(item.getId()!=null&&item.getId()>0){
                claimVoucherItemDao.update(item);
            }else{
                claimVoucherItemDao.insert(item);
            }
        }

    }

    @Override
    public void submit(int id) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        Employee employee = employeeDao.select(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_SUBMIT);
        claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(employee.getDepartmentSn(),Constant.POST_FM).get(0).getSn());
        claimVoucherDao.update(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(id);
        dealRecord.setComment("无");
        dealRecord.setDealResult(Constant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealWay(Constant.DEAL_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setDealSn(employee.getSn());
        dealRecordDao.insert(dealRecord);
    }

    @Override
    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        Employee employee = employeeDao.select(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());

        if(dealRecord.getDealWay().equals(Constant.DEAL_PASS)){
            if(claimVoucher.getTotalAmount()<=Constant.LIMIT_CHECK || employee.getPost().equals(Constant.POST_GM)){
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_CASHIER).get(0).getSn());
                System.out.println("test2");
                dealRecord.setDealResult(Constant.CLAIMVOUCHER_APPROVED);
            }else{
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_GM).get(0).getSn());

                dealRecord.setDealResult(Constant.CLAIMVOUCHER_RECHECK);
            }
        }else if(dealRecord.getDealWay().equals(Constant.DEAL_BACK)){
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealResult(Constant.CLAIMVOUCHER_BACK);
        }else if(dealRecord.getDealWay().equals(Constant.DEAL_REJECT)){
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Constant.CLAIMVOUCHER_TERMINATED);
        }else if(dealRecord.getDealWay().equals(Constant.DEAL_PAID)){
            claimVoucher.setStatus(Constant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealResult(Constant.CLAIMVOUCHER_PAID);
        }

        claimVoucherDao.update(claimVoucher);
        dealRecordDao.insert(dealRecord);
    }

}
