package com.ale.crud.bean;

import com.ale.crud.bean.test.FixedFee;
import com.ale.crud.bean.test.Renter;
import com.ale.crud.bean.test.WeFee;

import java.util.List;

public class Big {
    private Renter renter;
    private WeFee weFee;
    private List<FixedFee> fixedFee;

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public WeFee getWeFee() {
        return weFee;
    }

    public void setWeFee(WeFee weFee) {
        this.weFee = weFee;
    }

    public List<FixedFee> getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(List<FixedFee> fixedFee) {
        this.fixedFee = fixedFee;
    }
}