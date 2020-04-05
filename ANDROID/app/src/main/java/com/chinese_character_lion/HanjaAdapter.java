package com.chinese_character_lion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;


public class HanjaAdapter extends PagerAdapter {

    private List<Hanja> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public HanjaAdapter(List<Hanja> models, Context context) {
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
        View view = layoutInflater.inflate(R.layout.hanja_card, container, false);

        String CH_WORD = models.get(position).getHanjaText();
        String CH_MEAN = models.get(position).getHanjaMean();

        TextView HanjaText, HanjaMean;

        HanjaText = view.findViewById(R.id.hanja_text);
        HanjaMean = view.findViewById(R.id.hanja_mean);

        HanjaText.setText(CH_WORD);
        HanjaMean.setText(CH_MEAN);

        container.addView(view, 0);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
