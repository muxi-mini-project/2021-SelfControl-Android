package com.bignerdranch.android.sc.rank;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.Rank;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import static com.bignerdranch.android.sc.login.LoginActivity.token;


public class MonthRankFragment extends Fragment {

    private List<Rank> mList;
    private TextView n1, n2, n3, n4, n5, o1, o2, o3, o4, o5;
    private ImageView mExchange;
    private ImageButton thumb6,thumb7,thumb8,thumb9,thumb10;
    private ImageView user1,user2,user3,user4,user5;
    Animation shake;
    private User mUser;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_rank, container, false);

        n1 = view.findViewById(R.id.m_first_n);
        n2 = view.findViewById(R.id.m_second_n);
        n3 = view.findViewById(R.id.m_third_n);
        n4 = view.findViewById(R.id.m_fourth_n);
        n5 = view.findViewById(R.id.m_fifth_n);

        o1 = view.findViewById(R.id.m_first_o);
        o2 = view.findViewById(R.id.m_second_o);
        o3 = view.findViewById(R.id.m_third_o);
        o4 = view.findViewById(R.id.m_fourth_o);
        o5 = view.findViewById(R.id.m_fifth_o);

        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);

        thumb6 = view.findViewById(R.id.thumb6);
        thumb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb7 = view.findViewById(R.id.thumb7);
        thumb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb8 = view.findViewById(R.id.thumb8);
        thumb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb9 = view.findViewById(R.id.thumb9);
        thumb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb10 = view.findViewById(R.id.thumb10);
        thumb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });


        mExchange = view.findViewById(R.id.exchange);
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onCreateDialog();
            }
        });



        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        MonthRankClient client = retrofit.create(MonthRankClient.class);
        Call<List<Rank>> call = client.getMonth();

        call.enqueue(new Callback<List<Rank>>() {

            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                mList = response.body();

                if (mList == null) {
                    Toast.makeText(getActivity(), "当前暂无月榜打卡信息哟", Toast.LENGTH_SHORT).show();
                }
                if (mList != null) {
                    if (mList.size() == 1) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡天数：" + mList.get(0).getNumber());

                        user1 = view.findViewById(R.id.user1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id());
                            }
                        });

                    }
                    if (mList.size() == 2) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡天数：" + mList.get(0).getNumber());

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡天数：" + mList.get(1).getNumber());

                        user1 = view.findViewById(R.id.user1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id());
                            }
                        });

                        user2 = view.findViewById(R.id.user2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id());
                            }
                        });
                    }
                    if (mList.size() == 3) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡天数：" + mList.get(0).getNumber());

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡天数：" + mList.get(1).getNumber());

                        n3.setText(mList.get(2).getName());
                        o3.setText("打卡天数：" + mList.get(2).getNumber());

                        user1 = view.findViewById(R.id.user1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id());
                            }
                        });

                        user2 = view.findViewById(R.id.user2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id());
                            }
                        });
                        user3 = view.findViewById(R.id.user3);
                        user3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(2).getStudent_id());
                            }
                        });
                    }
                    if (mList.size() == 4) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡天数：" + mList.get(0).getNumber());

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡天数：" + mList.get(1).getNumber());

                        n3.setText(mList.get(2).getName());
                        o3.setText("打卡天数：" + mList.get(2).getNumber());

                        n4.setText(mList.get(3).getName());
                        o4.setText("打卡天数：" + mList.get(3).getNumber());

                        user1 = view.findViewById(R.id.user1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id());
                            }
                        });

                        user2 = view.findViewById(R.id.user2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id());
                            }
                        });
                        user3 = view.findViewById(R.id.user3);
                        user3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(2).getStudent_id());
                            }
                        });
                        user4 = view.findViewById(R.id.user4);
                        user4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(3).getStudent_id());
                            }
                        });
                    }
                    if (mList.size() == 5) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡天数：" + mList.get(0).getNumber());

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡天数：" + mList.get(1).getNumber());

                        n3.setText(mList.get(2).getName());
                        o3.setText("打卡天数：" + mList.get(2).getNumber());

                        n4.setText(mList.get(3).getName());
                        o4.setText("打卡天数：" + mList.get(3).getNumber());

                        n5.setText(mList.get(4).getName());
                        o5.setText("打卡天数：" + mList.get(4).getNumber());

                        user1 = view.findViewById(R.id.user1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id());
                            }
                        });

                        user2 = view.findViewById(R.id.user2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id());
                            }
                        });
                        user3 = view.findViewById(R.id.user3);
                        user3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(2).getStudent_id());
                            }
                        });
                        user4 = view.findViewById(R.id.user4);
                        user4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(3).getStudent_id());
                            }
                        });
                        user5 = view.findViewById(R.id.user5);
                        user5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(4).getStudent_id());
                            }
                        });
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {

            }
        });


        return view;
    }


    public void onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getActivity(),R.layout.rank_dialog,null));
        dialog.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.rank_dialog);
        EditText editText = window.findViewById(R.id.rank_editText);
        TextView textView = window.findViewById(R.id.rank_textView);
        Button Yes = window.findViewById(R.id.rank_yes);


        Button No = window.findViewById(R.id.rank_no);
        final int[] num = new int[1];
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String context = editText.getText().toString();
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(context);

                if(context != null && !context.equals("") && m.matches() && context.length() < 5){

                    if(Integer.parseInt(context) == 0) textView.setText(String.valueOf(0));
                    else{num[0] = (Integer.parseInt(context) - 1) * 2 + 50;
                        textView.setText(String.valueOf(num[0]));}

                }
                p = Pattern.compile("[a-zA-Z]");
                m = p.matcher(context);
                if(m.matches()) Toast.makeText(getActivity(),"请输入数字！！！",Toast.LENGTH_SHORT).show();
                if(context.equals(""))textView.setText(String.valueOf(0));
                if(context.length() >=5 ) Toast.makeText(getActivity(),"不要太贪心！！！",Toast.LENGTH_SHORT).show();
            }

        });
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request((num[0] - 50) /2);
            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private void showPrivateDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getActivity(), R.layout.dialog_private, null));
        dialog.show();
    }

    public class myRank{


        /**
         * ranking : 0
         */

        private int ranking;

        public myRank(int ranking){
            this.ranking = ranking;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

    }

    public interface MonthRankClient {
        @GET("api/v1/lists/month/")
        Call<List<Rank>> getMonth();
    }

    private void request(int number){

        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        RankBackgroundActivity.BuyRank client1 = retrofit1.create(RankBackgroundActivity.BuyRank.class);
        Call<myRank> call1 = client1.buyMonthRank(token,new myRank(number));

        call1.enqueue(new Callback<myRank>() {
            @Override
            public void onResponse(Call<myRank> call, Response<myRank> response) {
                if(response.code() == 200){
                    Toast.makeText(getActivity(),"兑换成功！",Toast.LENGTH_SHORT).show();
                }else if(response.code() == 201){
                    Toast.makeText(getActivity(),"错误:该用户兑换排名前没有该排名！",Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 203) {
                    Toast.makeText(getActivity(), "金币不足！快去打卡赚金币吧~", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    Toast.makeText(getActivity(), "出错啦！请稍后再试~", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 401) {
                    Toast.makeText(getActivity(), "身份认证出错啦！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "出错啦！稍后再试~", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<myRank> call, Throwable t) {
                Toast.makeText(getActivity(), "出错啦！请检查网络连接~", Toast.LENGTH_SHORT).show();


            }
        });


    }
    private void askPrivateRequest(String id){
        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        WeekRankFragment.AskPrivateAPI client = retrofit1.create(WeekRankFragment.AskPrivateAPI.class);
        Call<User> call = client.askPrivacy(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser = response.body();
                if(mUser != null){
                    if(mUser.getPrivacy() == 1){
                        showPrivateDialog();
                    }else if(mUser.getPrivacy() == 2){
//
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

}
