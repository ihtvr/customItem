package com.example.chenglinwei.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fangliju.commonitems.ItemSingle;

public class MainActivity extends AppCompatActivity {
    private String Tag = "MainActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();

    }

    ItemSingle clItem;
    LinearLayout ll_content;

    private void initView() {
        clItem = findViewById(R.id.clItem);
        ll_content = findViewById(R.id.ll_content);
        clItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(Tag, v.getId() + "");
            }
        });
        clItem.setSwitchCheckedChangeListener(new ItemSingle.OnSwitchCheckedChangeListener() {
            @Override
            public void onCheckedChangeListener(CompoundButton buttonView, boolean isChecked) {
                Log.e(Tag, isChecked + "");
            }
        });


        ItemSingle itemSingle = new ItemSingle.Builder(mContext)
                .setLeftText("leftleftleftleft")
                .setRightText("rirightrigh")
                .setDividersType(ItemSingle.DIVIDERS_BOTH)
                .setDividerTopMarginLeft(15)

                .create();
        ll_content.addView(itemSingle);
        itemSingle.setOnItemClickListener(new ItemSingle.OnItemClickListener() {
            @Override
            public void onClickListener(View view) {
                Log.e(Tag, view.getId() + "222222");
            }
        });
    }
}
