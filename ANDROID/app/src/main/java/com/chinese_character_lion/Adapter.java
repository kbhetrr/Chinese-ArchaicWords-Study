package com.chinese_character_lion;

import android.app.Activity;
import android.content.Context;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context mContext;
    private ArrayList<LionItem> mList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemclickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public Adapter(Context context, ArrayList<LionItem> List) {
        mContext = context;
        mList = List;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.lion_card, parent, false);
        return new ViewHolder(v);
    }

    public static String TagToString(String str) { //제목 특수문자 변환
        str = str.replaceAll("&lt;", "<");
        str = str.replaceAll("&gt;", ">");
        str = str.replaceAll("&quot;", "\"");
        str = str.replaceAll("&#39;", "'");
        str = str.replaceAll("&nbsp;", " ");
        str = str.replaceAll("&amp;", "&");
        str = str.replaceAll("&#35;", "#");

        return str;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        LionItem currentItem = mList.get(position);

        String Ch_Word = currentItem.getCh_Word();
        String Ko_Word = currentItem.getKo_Word();
        String Mean = currentItem.getMean();

        viewHolder.mNumberText.setText(Integer.toString(position + 1) + ".");
        viewHolder.mKoreanText.setText(Ko_Word);
        viewHolder.mChineseText.setText(Ch_Word);
        viewHolder.mMeaning.setText(Mean);
        //Picasso.with(mContext).load(imageUrl).fit().centerInside().into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNumberText;
        public TextView mKoreanText;
        public TextView mChineseText;
        public TextView mMeaning;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mNumberText = itemView.findViewById(R.id.text_number);
            mKoreanText = itemView.findViewById(R.id.text_korean);
            mChineseText = itemView.findViewById(R.id.text_chinese);
            mMeaning = itemView.findViewById(R.id.text_meaning);
        }
    }
}
