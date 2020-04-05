package com.chinese_character_lion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;

    int drawable_array[] = {R.drawable.gradient_1, R.drawable.gradient_2, R.drawable.gradient_3, R.drawable.gradient_4, R.drawable.gradient_5};

    public ViewPagerAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        String CH_WORD = models.get(position).getCh_word();

        if(CH_WORD.length() == 2) {

            View view = layoutInflater.inflate(R.layout.viewpager_two, container, false);

            //배경 색 변경
            CardView Card = view.findViewById(R.id.viewpager_card);
            //Card.setBackgroundResource(drawable_array[position % 5]);

            TextView desc;
            TextView ch_text1, ch_text2;
            TextView ko_text1, ko_text2;
            TextView ko_mean1, ko_mean2;

            desc = view.findViewById(R.id.desc);
            ch_text1 = view.findViewById(R.id.ch_text1);
            ch_text2 = view.findViewById(R.id.ch_text2);

            ko_text1 = view.findViewById(R.id.ko_text1);
            ko_text2 = view.findViewById(R.id.ko_text2);

            ko_mean1 = view.findViewById(R.id.ko_mean1);
            ko_mean2 = view.findViewById(R.id.ko_mean2);

            ko_text1.setText(models.get(position).getTitle().substring(0, 1));
            ko_text2.setText(models.get(position).getTitle().substring(1, 2));

            ArrayList<String> ch_mean = models.get(position).getCh_mean();

            ch_text1.setText(CH_WORD.substring(0, 1));
            ch_text2.setText(CH_WORD.substring(1, 2));

            ko_mean1.setText(ch_mean.get(0));
            ko_mean2.setText(ch_mean.get(1));

            desc.setText(models.get(position).getDesc());
            /*
            ArrayList<String> Category = models.get(position).getCategory();

            LinearLayout tag_layout = view.findViewById(R.id.tag_layout);

            for (int i = 0; i < Category.size(); i++) {
                TextView textView = new TextView(context);
                textView.setText("#" + Category.get(i));
                textView.setTextSize(12);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.drawable.tag_background);
                textView.setPadding(24, 24, 24, 24);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(16, 0, 0, 0);
                lp.gravity = Gravity.CENTER;
                textView.setLayoutParams(lp);

                tag_layout.addView(textView);
            }
            */
            container.addView(view, 0);

            return view;

        } else if (CH_WORD.length() == 3) {

            View view = layoutInflater.inflate(R.layout.viewpager_three, container, false);

            //배경 색 변경
            CardView Card = view.findViewById(R.id.viewpager_card);
            //Card.setBackgroundResource(drawable_array[position % 5]);

            TextView desc;
            TextView ch_text1, ch_text2, ch_text3;
            TextView ko_text1, ko_text2, ko_text3;
            TextView ko_mean1, ko_mean2, ko_mean3;

            desc = view.findViewById(R.id.desc);
            ch_text1 = view.findViewById(R.id.ch_text1);
            ch_text2 = view.findViewById(R.id.ch_text2);
            ch_text3 = view.findViewById(R.id.ch_text3);

            ko_text1 = view.findViewById(R.id.ko_text1);
            ko_text2 = view.findViewById(R.id.ko_text2);
            ko_text3 = view.findViewById(R.id.ko_text3);

            ko_mean1 = view.findViewById(R.id.ko_mean1);
            ko_mean2 = view.findViewById(R.id.ko_mean2);
            ko_mean3 = view.findViewById(R.id.ko_mean3);

            ko_text1.setText(models.get(position).getTitle().substring(0, 1));
            ko_text2.setText(models.get(position).getTitle().substring(1, 2));
            ko_text3.setText(models.get(position).getTitle().substring(2, 3));

            ArrayList<String> ch_mean = models.get(position).getCh_mean();

            ch_text1.setText(CH_WORD.substring(0, 1));
            ch_text2.setText(CH_WORD.substring(1, 2));
            ch_text3.setText(CH_WORD.substring(2, 3));

            ko_mean1.setText(ch_mean.get(0));
            ko_mean2.setText(ch_mean.get(1));
            ko_mean3.setText(ch_mean.get(2));

            desc.setText(models.get(position).getDesc());
            /*
            ArrayList<String> Category = models.get(position).getCategory();

            LinearLayout tag_layout = view.findViewById(R.id.tag_layout);

            for (int i = 0; i < Category.size(); i++) {
                TextView textView = new TextView(context);
                textView.setText("#" + Category.get(i));
                textView.setTextSize(12);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.drawable.tag_background);
                textView.setPadding(24, 24, 24, 24);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(16, 0, 0, 0);
                lp.gravity = Gravity.CENTER;
                textView.setLayoutParams(lp);

                tag_layout.addView(textView);
            }
            */
            container.addView(view, 0);

            return view;

        } else if (CH_WORD.length() == 4) {

            View view = layoutInflater.inflate(R.layout.viewpager_four, container, false);

            //배경 색 변경
            CardView Card = view.findViewById(R.id.viewpager_card);
            //Card.setBackgroundResource(drawable_array[position % 5]);

            final TextView desc;
            TextView ch_text1, ch_text2, ch_text3, ch_text4;
            TextView ko_text1, ko_text2, ko_text3, ko_text4;
            TextView ko_mean1, ko_mean2, ko_mean3, ko_mean4;

            desc = view.findViewById(R.id.desc);
            ch_text1 = view.findViewById(R.id.ch_text1);
            ch_text2 = view.findViewById(R.id.ch_text2);
            ch_text3 = view.findViewById(R.id.ch_text3);
            ch_text4 = view.findViewById(R.id.ch_text4);

            ko_text1 = view.findViewById(R.id.ko_text1);
            ko_text2 = view.findViewById(R.id.ko_text2);
            ko_text3 = view.findViewById(R.id.ko_text3);
            ko_text4 = view.findViewById(R.id.ko_text4);

            ko_mean1 = view.findViewById(R.id.ko_mean1);
            ko_mean2 = view.findViewById(R.id.ko_mean2);
            ko_mean3 = view.findViewById(R.id.ko_mean3);
            ko_mean4 = view.findViewById(R.id.ko_mean4);

            ko_text1.setText(models.get(position).getTitle().substring(0, 1));
            ko_text2.setText(models.get(position).getTitle().substring(1, 2));
            ko_text3.setText(models.get(position).getTitle().substring(2, 3));
            ko_text4.setText(models.get(position).getTitle().substring(3, 4));

            ArrayList<String> ch_mean = models.get(position).getCh_mean();

            ch_text1.setText(CH_WORD.substring(0, 1));
            ch_text2.setText(CH_WORD.substring(1, 2));
            ch_text3.setText(CH_WORD.substring(2, 3));
            ch_text4.setText(CH_WORD.substring(3, 4));

            ko_mean1.setText(ch_mean.get(0));
            ko_mean2.setText(ch_mean.get(1));
            ko_mean3.setText(ch_mean.get(2));
            ko_mean4.setText(ch_mean.get(3));

            desc.setText(models.get(position).getDesc());

            /*
            ArrayList<String> Category = models.get(position).getCategory();

            LinearLayout tag_layout = view.findViewById(R.id.tag_layout);

            for (int i = 0; i < Category.size(); i++) {
                TextView textView = new TextView(context);
                textView.setText("#" + Category.get(i));
                textView.setTextSize(12);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.drawable.tag_background);
                textView.setPadding(24, 24, 24, 24);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(16, 0, 0, 0);
                lp.gravity = Gravity.CENTER;
                textView.setLayoutParams(lp);

                tag_layout.addView(textView);
            }
            */
            container.addView(view, 0);

            return view;

        } else if (CH_WORD.length() == 5) {

            View view = layoutInflater.inflate(R.layout.viewpager_five, container, false);

            //배경 색 변경
            CardView Card = view.findViewById(R.id.viewpager_card);
            //Card.setBackgroundResource(drawable_array[position % 5]);

            TextView desc;
            TextView ch_text1, ch_text2, ch_text3, ch_text4, ch_text5;
            TextView ko_text1, ko_text2, ko_text3, ko_text4, ko_text5;
            TextView ko_mean1, ko_mean2, ko_mean3, ko_mean4, ko_mean5;

            desc = view.findViewById(R.id.desc);
            ch_text1 = view.findViewById(R.id.ch_text1);
            ch_text2 = view.findViewById(R.id.ch_text2);
            ch_text3 = view.findViewById(R.id.ch_text3);
            ch_text4 = view.findViewById(R.id.ch_text4);
            ch_text5 = view.findViewById(R.id.ch_text5);

            ko_text1 = view.findViewById(R.id.ko_text1);
            ko_text2 = view.findViewById(R.id.ko_text2);
            ko_text3 = view.findViewById(R.id.ko_text3);
            ko_text4 = view.findViewById(R.id.ko_text4);
            ko_text5 = view.findViewById(R.id.ko_text5);

            ko_mean1 = view.findViewById(R.id.ko_mean1);
            ko_mean2 = view.findViewById(R.id.ko_mean2);
            ko_mean3 = view.findViewById(R.id.ko_mean3);
            ko_mean4 = view.findViewById(R.id.ko_mean4);
            ko_mean5 = view.findViewById(R.id.ko_mean5);

            ko_text1.setText(models.get(position).getTitle().substring(0, 1));
            ko_text2.setText(models.get(position).getTitle().substring(1, 2));
            ko_text3.setText(models.get(position).getTitle().substring(2, 3));
            ko_text4.setText(models.get(position).getTitle().substring(3, 4));
            ko_text5.setText(models.get(position).getTitle().substring(4, 5));

            ArrayList<String> ch_mean = models.get(position).getCh_mean();

            ch_text1.setText(CH_WORD.substring(0, 1));
            ch_text2.setText(CH_WORD.substring(1, 2));
            ch_text3.setText(CH_WORD.substring(2, 3));
            ch_text4.setText(CH_WORD.substring(3, 4));
            ch_text5.setText(CH_WORD.substring(4, 5));

            ko_mean1.setText(ch_mean.get(0));
            ko_mean2.setText(ch_mean.get(1));
            ko_mean3.setText(ch_mean.get(2));
            ko_mean4.setText(ch_mean.get(3));
            ko_mean5.setText(ch_mean.get(4));

            desc.setText(models.get(position).getDesc());
            /*
            ArrayList<String> Category = models.get(position).getCategory();

            LinearLayout tag_layout = view.findViewById(R.id.tag_layout);

            for (int i = 0; i < Category.size(); i++) {
                TextView textView = new TextView(context);
                textView.setText("#" + Category.get(i));
                textView.setTextSize(12);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.drawable.tag_background);
                textView.setPadding(24, 24, 24, 24);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(16, 0, 0, 0);
                lp.gravity = Gravity.CENTER;
                textView.setLayoutParams(lp);

                tag_layout.addView(textView);
            }
            */
            container.addView(view, 0);

            return view;

        } else if (CH_WORD.length() == 6) {

            View view = layoutInflater.inflate(R.layout.viewpager_six, container, false);

            //배경 색 변경
            CardView Card = view.findViewById(R.id.viewpager_card);
            //Card.setBackgroundResource(drawable_array[position % 5]);

            TextView desc;
            TextView ch_text1, ch_text2, ch_text3, ch_text4, ch_text5, ch_text6;
            TextView ko_text1, ko_text2, ko_text3, ko_text4, ko_text5, ko_text6;
            TextView ko_mean1, ko_mean2, ko_mean3, ko_mean4, ko_mean5, ko_mean6;

            desc = view.findViewById(R.id.desc);
            ch_text1 = view.findViewById(R.id.ch_text1);
            ch_text2 = view.findViewById(R.id.ch_text2);
            ch_text3 = view.findViewById(R.id.ch_text3);
            ch_text4 = view.findViewById(R.id.ch_text4);
            ch_text5 = view.findViewById(R.id.ch_text5);
            ch_text6 = view.findViewById(R.id.ch_text6);

            ko_text1 = view.findViewById(R.id.ko_text1);
            ko_text2 = view.findViewById(R.id.ko_text2);
            ko_text3 = view.findViewById(R.id.ko_text3);
            ko_text4 = view.findViewById(R.id.ko_text4);
            ko_text5 = view.findViewById(R.id.ko_text5);
            ko_text6 = view.findViewById(R.id.ko_text6);

            ko_mean1 = view.findViewById(R.id.ko_mean1);
            ko_mean2 = view.findViewById(R.id.ko_mean2);
            ko_mean3 = view.findViewById(R.id.ko_mean3);
            ko_mean4 = view.findViewById(R.id.ko_mean4);
            ko_mean5 = view.findViewById(R.id.ko_mean5);
            ko_mean6 = view.findViewById(R.id.ko_mean6);

            ko_text1.setText(models.get(position).getTitle().substring(0, 1));
            ko_text2.setText(models.get(position).getTitle().substring(1, 2));
            ko_text3.setText(models.get(position).getTitle().substring(2, 3));
            ko_text4.setText(models.get(position).getTitle().substring(3, 4));
            ko_text5.setText(models.get(position).getTitle().substring(4, 5));
            ko_text6.setText(models.get(position).getTitle().substring(5, 6));

            ArrayList<String> ch_mean = models.get(position).getCh_mean();

            ch_text1.setText(CH_WORD.substring(0, 1));
            ch_text2.setText(CH_WORD.substring(1, 2));
            ch_text3.setText(CH_WORD.substring(2, 3));
            ch_text4.setText(CH_WORD.substring(3, 4));
            ch_text5.setText(CH_WORD.substring(4, 5));
            ch_text6.setText(CH_WORD.substring(5, 6));

            ko_mean1.setText(ch_mean.get(0));
            ko_mean2.setText(ch_mean.get(1));
            ko_mean3.setText(ch_mean.get(2));
            ko_mean4.setText(ch_mean.get(3));
            ko_mean5.setText(ch_mean.get(4));
            ko_mean6.setText(ch_mean.get(5));

            desc.setText(models.get(position).getDesc());
            /*
            ArrayList<String> Category = models.get(position).getCategory();

            LinearLayout tag_layout = view.findViewById(R.id.tag_layout);

            for (int i = 0; i < Category.size(); i++) {
                TextView textView = new TextView(context);
                textView.setText("#" + Category.get(i));
                textView.setTextSize(12);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundResource(R.drawable.tag_background);
                textView.setPadding(24, 24, 24, 24);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(16, 0, 0, 0);
                lp.gravity = Gravity.CENTER;
                textView.setLayoutParams(lp);

                tag_layout.addView(textView);
            }
            */
            container.addView(view, 0);

            return view;

        } else {
            return true;
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
