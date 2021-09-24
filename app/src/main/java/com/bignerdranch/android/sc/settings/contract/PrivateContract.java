package com.bignerdranch.android.sc.settings.contract;

public interface PrivateContract {
    interface VP{
        void trueRequest(String token);
        void falseRequest(String token);
        void success();
        void fail();
    }
    interface M{
        void request(Integer type,String token);
    }
}
