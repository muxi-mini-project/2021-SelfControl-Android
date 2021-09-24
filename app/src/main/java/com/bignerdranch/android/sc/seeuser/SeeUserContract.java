package com.bignerdranch.android.sc.seeuser;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeeUserContract {

    interface VP{
        void getLabel(String id);
        void Fail();
        void haveList(List mList);
        void listNull();
    }

    interface M{
        void getList(String id);
    }
}
