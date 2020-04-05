package com.chinese_character_lion;

import android.animation.ArgbEvaluator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable animDrawable;

    ProgressDialog progressDialog;
    String title;

    ArrayList<String> Ch_Word, Ko_Word, Meaning;
    ArrayList<LionItem> mList;

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    HanjaAdapter viewPagerAdapter2;
    List<Model> models;
    List<Hanja> models2;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    ConstraintLayout Main_Layout;
    TextView ViewPager_Count, ViewPager_All;
    int count, all;

    TextView User_Information, User_Name;

    FloatingActionButton Write;

    ChipGroup HanjaLionGroup;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*User_Information = findViewById(R.id.user_information);
        User_Information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });*/

        progressBar = findViewById(R.id.Progress_Bar);

        HanjaLionGroup = findViewById(R.id.hanja_lion_radiogroup);
        HanjaLionGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                if (i == R.id.hanja_button) {
                    new Lion2Hanja().execute();
                } else if (i == R.id.lion_button) {
                    new Hanja2Lion().execute();
                }
            }
        });

        ViewPager_Count = findViewById(R.id.viewpager_count);
        ViewPager_All = findViewById(R.id.viewpager_all);

        Ch_Word = new ArrayList<>();
        Ko_Word = new ArrayList<>();
        Meaning = new ArrayList<>();

        mList = new ArrayList<>();

        //getWeb getWeb = new getWeb();
        //getWeb.execute(url + PageNumber);

        viewPager = findViewById(R.id.viewPager);

        final Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        colors = colors_temp;

        // 처음 고사성어 생성
        models = new ArrayList<>();
        models2 = new ArrayList<>();

        // 처음 한 번만 파싱해오기
        jsonParsing(getJsonString());
        HanjaParsing(getJsonString2());

        viewPagerAdapter = new ViewPagerAdapter(models, MainActivity.this);
        viewPager.setAdapter(viewPagerAdapter);

        all = viewPagerAdapter.getCount();
        ViewPager_All.setText(String.valueOf(all));

        int dpValue = 40;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);

        viewPager.setPadding(margin, 0, margin, 0);

        //일정 시간마다 그라데이션 색 변경
        Main_Layout = findViewById(R.id.main_layout);
        animDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.bg_transition01);
        if (animDrawable != null) {
            animDrawable.setEnterFadeDuration(10000);
            animDrawable.setExitFadeDuration(10000);
        }
        viewPager.getRootView().setBackground(animDrawable);
        //Main_Layout.setBackground(animDrawable);


        // 뷰페이저 인디케이터 (페이지 번호 수) 설정
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //페이지 카운트 설정
                count = position;
                ViewPager_Count.setText(String.valueOf(count + 1));
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Write = findViewById(R.id.btnOrder);
        Write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                Model model_tmp = models.get(viewPager.getCurrentItem());
                intent.putExtra("ko_word", model_tmp.getTitle());
                intent.putExtra("ch_word", model_tmp.getCh_word());
                startActivity(intent);
            }
        });

        /*Main_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!Main_recyclerview.canScrollVertically(1)) {
                    //끝까지 내렸을 때

                    PageNumber += 1;

                    getWeb getWeb1 = new getWeb();
                    getWeb1.execute(url + PageNumber);
                }

            }
        });*/

    }

    private void jsonParsing(String json)
    {
        try{

            JSONObject array = new JSONObject(json);
            for(int i = 1; i <= 4687; i++)
            {
                if (array.has(String.valueOf(i)) == false) {
                    continue;
                }
                JSONObject Object = array.getJSONObject(String.valueOf(i));
                String ch = Object.getString("chinese_word");
                String ko = Object.getString("korean_word");
                String mean = Object.getString("mean");
                //JSONObject categorys = Object.getJSONObject("category");

                ArrayList<String> category;
                category = new ArrayList<>();
                /*
                for (int j = 1; j <= categorys.length(); j++) {
                    String tmp = categorys.getString(String.valueOf(j));
                    category.add(tmp);
                }*/

                ArrayList<String> ch_mean;

                ch_mean = new ArrayList<>();

                for (int j = 1; j <= ch.length(); j++) {
                    JSONArray tmp = Object.getJSONArray(String.valueOf(j));
                    if (tmp.length() == 1) {
                        JSONObject t = tmp.getJSONObject(0);
                        ch_mean.add(t.getString("def") + ' ' + t.getString("kor"));
                    } else {
                        String s_temp = "";
                        for (int k = 0; k < tmp.length(); k++) {
                            JSONObject t = tmp.getJSONObject(k);
                            s_temp += t.getString("def") + ' ' + t.getString("kor");
                            if (k != tmp.length() - 1) {
                                s_temp += ", ";
                            }
                        }
                        ch_mean.add(s_temp);
                    }
                }
                mList.add(new LionItem(ch, ko, mean));
                models.add(new Model(ch, ko, mean, ch_mean));
            }

            //mAdapter = new Adapter(MainActivity.this, mList);
            //Main_recyclerview.setAdapter(mAdapter);

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void HanjaParsing(String json)
    {
        try{
            Log.e("JSOn", json);
            JSONObject jsonObject = new JSONObject(json);
            String hanja = jsonObject.getString("hanja");
            JSONObject array = new JSONObject(hanja);
            List<String> KEY = new ArrayList<>();
            Iterator It = array.keys();
            while (It.hasNext()) {
                KEY.add(It.next().toString());
            }
            for (int i = 0; i < KEY.size(); i++) {
                String ch = KEY.get(i);
                Log.v("Lion2Hanja", ch);
                JSONArray JArray = array.getJSONArray(ch);

                String ch_mean;

                ch_mean = "";

                for (int j = 0; j < JArray.length(); j++) {
                    JSONObject t = JArray.getJSONObject(j);
                    ch_mean += t.getString("def") + ' ' + t.getString("kor");
                    if (j != JArray.length() - 1) {
                        ch_mean += ", ";
                    }
                }

                models2.add(new Hanja(ch, ch_mean));
            }
            //mAdapter = new Adapter(MainActivity.this, mList);
            //Main_recyclerview.setAdapter(mAdapter);

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getAssets().open("lion_data_result.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    private String getJsonString2()
    {
        String json2 = "";
        try {
            InputStream is2 = getAssets().open("hanjadata.json");
            int fileSize2 = is2.available();

            byte[] buffer2 = new byte[fileSize2];
            is2.read(buffer2);
            is2.close();

            json2 = new String(buffer2, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json2;
    }


    public class Hanja2Lion extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ArrayList<Model> M = new ArrayList<>();
            viewPagerAdapter = new ViewPagerAdapter(M, MainActivity.this);
            viewPager.setAdapter(viewPagerAdapter);

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            viewPagerAdapter = new ViewPagerAdapter(models, MainActivity.this);
            viewPager.setAdapter(viewPagerAdapter);

            all = viewPagerAdapter.getCount();
            ViewPager_All.setText(String.valueOf(all));

            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }

    public class Lion2Hanja extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ArrayList<Model> M = new ArrayList<>();
            viewPagerAdapter = new ViewPagerAdapter(M, MainActivity.this);
            viewPager.setAdapter(viewPagerAdapter);

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            viewPagerAdapter2 = new HanjaAdapter(models2, MainActivity.this);
            viewPager.setAdapter(viewPagerAdapter2);

            all = viewPagerAdapter2.getCount();
            ViewPager_All.setText(String.valueOf(all));

            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        animDrawable.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        animDrawable.stop();
    }
}
