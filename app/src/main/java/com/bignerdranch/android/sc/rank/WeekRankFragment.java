package com.bignerdranch.android.sc.rank;

import androidx.fragment.app.Fragment;

public class WeekRankFragment extends Fragment {
/*
    private List<Rank> mList;
    private TextView n1, n2, n3, n4, n5, o1, o2, o3, o4, o5;
    private ImageView user1,user2,user3,user4,user5;
    private ImageView mExchange;
    private ImageButton thumb1, thumb2, thumb3, thumb4, thumb5;

    Animation shake;
    private String name;
    private String id;
    private User.DataDTO mUser;

    public final static String userId = "USERID";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_rank, container, false);

        n1 = view.findViewById(R.id.w_first_n);
        n2 = view.findViewById(R.id.w_second_n);
        n3 = view.findViewById(R.id.w_third_n);
        n4 = view.findViewById(R.id.w_fourth_n);
        n5 = view.findViewById(R.id.w_fifth_n);

        o1 = view.findViewById(R.id.w_first_o);
        o2 = view.findViewById(R.id.w_second_o);
        o3 = view.findViewById(R.id.w_third_o);
        o4 = view.findViewById(R.id.w_fourth_o);
        o5 = view.findViewById(R.id.w_fifth_o);



        mExchange = view.findViewById(R.id.exchange_week);
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog();
            }
        });

        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);

        thumb1 = view.findViewById(R.id.thumb1);
        thumb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb2 = view.findViewById(R.id.thumb2);
        thumb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb3 = view.findViewById(R.id.thumb3);
        thumb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb4 = view.findViewById(R.id.thumb4);
        thumb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);
            }
        });

        thumb5 = view.findViewById(R.id.thumb5);
        thumb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shake);

            }
        });

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WeekRankClient client = retrofit.create(WeekRankClient.class);
        Call<List<Rank>> call = client.getWeek();

        call.enqueue(new Callback<List<Rank>>() {

            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                mList = response.body();

                if (mList == null) {
                    Toast.makeText(getActivity(), "当前暂无周榜排行榜信息！", Toast.LENGTH_SHORT).show();
                }
                if (mList != null) {
                    if (mList.size() == 1) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡次数：" + mList.get(0).getNumber());

                        user1 = view.findViewById(R.id.wuser1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id(),mList.get(0).getName());
                            }
                        });
                    }
                    if (mList.size() == 2) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡次数：" + mList.get(0).getNumber());

                        user1 = view.findViewById(R.id.wuser1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id(),mList.get(0).getName());
                            }
                        });

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡次数：" + mList.get(1).getNumber());

                        user2 = view.findViewById(R.id.wuser2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id(),mList.get(1).getName());
                            }
                        });
                    }
                    if (mList.size() == 3) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡次数：" + mList.get(0).getNumber());

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡次数：" + mList.get(1).getNumber());

                        n3.setText(mList.get(2).getName());
                        o3.setText("打卡次数：" + mList.get(2).getNumber());
                        user1 = view.findViewById(R.id.wuser1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id(),mList.get(0).getName());
                            }
                        });

                        user2 = view.findViewById(R.id.wuser2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id(),mList.get(1).getName());
                            }
                        });
                        user3 = view.findViewById(R.id.wuser3);
                        user3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(2).getStudent_id(),mList.get(2).getName());
                            }
                        });

                    }
                    if (mList.size() == 4) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡次数：" + mList.get(0).getNumber());

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡次数：" + mList.get(1).getNumber());

                        n3.setText(mList.get(2).getName());
                        o3.setText("打卡次数：" + mList.get(2).getNumber());

                        n4.setText(mList.get(3).getName());
                        o4.setText("打卡次数：" + mList.get(3).getNumber());
                        user1 = view.findViewById(R.id.wuser1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id(),mList.get(0).getName());
                            }
                        });

                        user2 = view.findViewById(R.id.wuser2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id(),mList.get(1).getName());
                            }
                        });
                        user3 = view.findViewById(R.id.wuser3);
                        user3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(2).getStudent_id(),mList.get(2).getName());
                            }
                        });
                        user4 = view.findViewById(R.id.wuser4);
                        user4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(3).getStudent_id(),mList.get(3).getName());
                            }
                        });
                    }
                    if (mList.size() == 5) {
                        n1.setText(mList.get(0).getName());
                        o1.setText("打卡次数：" + mList.get(0).getNumber());

                        n2.setText(mList.get(1).getName());
                        o2.setText("打卡次数：" + mList.get(1).getNumber());

                        n3.setText(mList.get(2).getName());
                        o3.setText("打卡次数：" + mList.get(2).getNumber());

                        n4.setText(mList.get(3).getName());
                        o4.setText("打卡次数：" + mList.get(3).getNumber());

                        n5.setText(mList.get(4).getName());
                        o5.setText("打卡次数：" + mList.get(4).getNumber());

                        user1 = view.findViewById(R.id.wuser1);
                        user1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(0).getStudent_id(),mList.get(0).getName());
                            }
                        });

                        user2 = view.findViewById(R.id.wuser2);
                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(1).getStudent_id(),mList.get(1).getName());
                            }
                        });
                        user3 = view.findViewById(R.id.wuser3);
                        user3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(2).getStudent_id(),mList.get(2).getName());
                            }
                        });
                        user4 = view.findViewById(R.id.wuser4);
                        user4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(3).getStudent_id(),mList.get(3).getName());
                            }
                        });
                        user5 = view.findViewById(R.id.wuser5);
                        user5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                askPrivateRequest(mList.get(4).getStudent_id(),mList.get(4).getName());
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

    public interface WeekRankClient {
        @GET("api/v1/lists/week/")
        Call<List<Rank>> getWeek();
    }

    public void onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getActivity(), R.layout.rank_dialog, null));
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

                if (context != null && !context.equals("") && m.matches() && context.length() < 5) {
                    if(Integer.parseInt(context) == 0) textView.setText(String.valueOf(0));
                    else{num[0] = (Integer.parseInt(context) - 1) * 2 + 50;
                    textView.setText(String.valueOf(num[0]));}

                }
                p = Pattern.compile("[a-zA-Z]");
                m = p.matcher(context);
                if (m.matches())
                    Toast.makeText(getActivity(), "请输入数字！！！", Toast.LENGTH_SHORT).show();
                if (context.equals("")) textView.setText(String.valueOf(0));
                if (context.length() >= 5)
                    Toast.makeText(getActivity(), "不要太贪心！！！", Toast.LENGTH_SHORT).show();
            }

        });
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request((num[0] - 50) / 2);
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
        View v = View.inflate(getContext(), R.layout.dialog_private, null);
        Button know = v.findViewById(R.id.know);
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setView(v);
        dialog.show();
    }

    private void request(int number) {

        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

       /* Retrofit retrofit1 = builder1.build();
        RankBackgroundActivity.BuyRank client1 = retrofit1.create(RankBackgroundActivity.BuyRank.class);
        Call<MonthRankFragment.myRank> call1 = client1.buyWeekRank(token, new myRank(number));

        call1.enqueue(new Callback<MonthRankFragment.myRank>() {
            @Override
            public void onResponse(Call<MonthRankFragment.myRank> call, Response<MonthRankFragment.myRank> response) {
                if (response.code() == 200) {
                    Toast.makeText(getActivity(), "兑换成功！", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 201) {
                    Toast.makeText(getActivity(), "错误:该用户兑换排名前没有该排名！", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 203) {
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
            public void onFailure(Call<MonthRankFragment.myRank> call, Throwable t) {
                Toast.makeText(getActivity(), "出错啦！请检查网络连接~", Toast.LENGTH_SHORT).show();


            }
        });
    }


        /**
         * ranking : 0


        private int ranking;

            this.ranking = ranking;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

    }

    public interface AskPrivateAPI{
        @GET("api/v1/user/privacy/{id}")
        Call<User.DataDTO> askPrivacy(@Path("id") String id);
    }
    private void askPrivateRequest(String id,String name){
        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        AskPrivateAPI client = retrofit1.create(AskPrivateAPI.class);
        Call<User.DataDTO> call = client.askPrivacy(id);

        call.enqueue(new Callback<User.DataDTO>() {
            @Override
            public void onResponse(Call<User.DataDTO> call, Response<User.DataDTO> response) {
                mUser = response.body();
                if(mUser != null){
                    if(mUser.getPrivacy() == 2){
                        showPrivateDialog();
                    }else if(mUser.getPrivacy() == 1){
                        Intent intent = new Intent(getActivity(), UserLabelActivity.class);
                        intent.putExtra("data",id);
                        intent.putExtra("data1",name);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<User.DataDTO> call, Throwable t) {

            }
        });

    }*/

}
