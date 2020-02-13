package com.chinese_character_lion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class UserActivity extends AppCompatActivity {

    Chip Normal_Button;
    ChipGroup Chip_Group;

    LinearLayout User_Selected_Chip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        User_Selected_Chip = findViewById(R.id.user_selected_chip);
        Chip_Group = findViewById(R.id.chip_group1);

        //Normal_Button = findViewById(R.id.normal_button);
        /*Normal_Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Normal_Button.setChipIcon(getResources().getDrawable(R.drawable.ic_check_black_24dp));
                    Normal_Button.setTextColor(getResources().getColor(R.color.black));
                } else if (!isChecked) {
                    Normal_Button.setChipIcon(getResources().getDrawable(R.drawable.ic_add_white_24dp));
                    Normal_Button.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });*/

        Chip_Group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, int i) {
                Chip chip = chipGroup.findViewById(i);

                TextView textView = new TextView(UserActivity.this);
                textView.setText(chip.getText());
                textView.setTextSize(12);
                textView.setTextColor(Color.BLACK);
                textView.setBackgroundResource(R.drawable.tag_background);
                textView.setPadding(24, 24, 24, 24);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(16, 0, 0, 0);
                lp.gravity = Gravity.CENTER;
                textView.setLayoutParams(lp);

                User_Selected_Chip.addView(textView);
            }
        });

    }
}
