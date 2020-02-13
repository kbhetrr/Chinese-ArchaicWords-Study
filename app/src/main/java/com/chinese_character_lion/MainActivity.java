package com.chinese_character_lion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    RecyclerView Main_recyclerview;

    ProgressDialog progressDialog;
    String title;

    ArrayList<String> Ch_Word, Ko_Word, Meaning;
    ArrayList<LionItem> mList;

    Adapter mAdapter;

    int PageNumber = 1;
    String url = "https://hanja.dict.naver.com/category/subject?q=%EA%B8%B0%ED%83%80&pageNo=";

    TextView context1;
    String CH = "";

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    LinearLayout Main_Layout;
    TextView ViewPager_Count, ViewPager_All;
    int count, all;

    CircleImageView User_Image;
    TextView User_Information, User_Name;

    FloatingActionButton Write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Write = findViewById(R.id.btnOrder);
        Write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });

        User_Name= findViewById(R.id.user_name);
        User_Image = findViewById(R.id.user_image);
        User_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String> (User_Image, "User_Image");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);

                startActivity(intent, options.toBundle());
            }
        });

        User_Information = findViewById(R.id.user_information);
        User_Information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        ViewPager_Count = findViewById(R.id.viewpager_count);
        ViewPager_All = findViewById(R.id.viewpager_all);

        context1 = findViewById(R.id.context1);

        Ch_Word = new ArrayList<>();
        Ko_Word = new ArrayList<>();
        Meaning = new ArrayList<>();

        mList = new ArrayList<>();

        Main_recyclerview = findViewById(R.id.main_recyclerview);
        Main_recyclerview.setHasFixedSize(true);
        Main_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

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

        models = new ArrayList<>();

        jsonParsing(getJsonString());
        context1.setText(CH);

        all = viewPagerAdapter.getCount();
        ViewPager_All.setText(String.valueOf(all));

        int dpValue = 35;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);

        viewPager.setPadding(margin, 0, margin, 0);

        Main_Layout = findViewById(R.id.main_layout);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //페이지 카운트 설정
                count = position;
                ViewPager_Count.setText(String.valueOf(count + 1));

                if (position < (viewPagerAdapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.getRootView().setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));
                } else {
                    viewPager.getRootView().setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

            for(int i = 1; i <= array.length(); i++)
            {
                JSONObject Object = array.getJSONObject(String.valueOf(i));
                String ch = Object.getString("chinese_word");
                String ko = Object.getString("korean_word");
                String mean = Object.getString("mean");
                JSONObject categorys = Object.getJSONObject("category");

                ArrayList<String> category;

                category = new ArrayList<>();

                for (int j = 1; j <= categorys.length(); j++) {
                    String tmp = categorys.getString(String.valueOf(j));
                    category.add(tmp);
                }

                ArrayList<String> ch_mean;

                ch_mean = new ArrayList<>();

                for (int j = 1; j <= ch.length(); j++) {
                    String tmp = Object.getString(String.valueOf(j));
                    ch_mean.add(tmp);
                }

                mList.add(new LionItem(ch, ko, mean));

                if (ch.length() < 7) {
                    models.add(new Model(ch, ko, mean, ch_mean, category));
                }
            }

            mAdapter = new Adapter(MainActivity.this, mList);
            Main_recyclerview.setAdapter(mAdapter);

            viewPagerAdapter = new ViewPagerAdapter(models, this);
            viewPager.setAdapter(viewPagerAdapter);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getAssets().open("etc_4.json");
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

    public class getWeb extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Ch_Word = new ArrayList<>();
            Ko_Word = new ArrayList<>();
            Meaning = new ArrayList<>();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            for(int i = 0; i < Meaning.size(); i++) {
                mList.add(new LionItem(Ch_Word.get(i), Ko_Word.get(i), Meaning.get(i)));
            }

            if(PageNumber == 81) {
                String cch = "";
                for(int i = 0; i < 81; i++) {
                    cch += mList.get(i).getCh_Word();
                }
                context1.setText(cch);
            }

            if(PageNumber == 1) {
                //mAdapter = new Adapter(MainActivity.this, List);
                //Main_recyclerview.setAdapter(mAdapter);
            } else {
                //mAdapter.notifyDataSetChanged();
            }

            //textview.setText(title);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(String... URL) {

            try {
                Document doc = Jsoup.connect(URL[0]).get();
                Element body = doc.body();
                Elements div = body.getElementsByTag("div");
                Elements context = div.select(".result_chn_words");
                //title = div.select(".result_chn_words").html();

                Elements Chinese_Word = context.select("dt > a");

                for(Element element : Chinese_Word) {
                    String ch = element.text();
                    Ch_Word.add(ch);
                }

                Elements Korean_Word = context.select("dd > a");

                for(Element element : Korean_Word) {
                    String ko = element.text();
                    Ko_Word.add(ko);
                }

                Elements meaning = body.getElementsByTag("dd").select(".meaning");

                for (Element element : meaning) {
                    String mean = element.text();
                    Meaning.add(mean);
                }

                //title = div.html();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
