package com.example.chenglinwei.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fangliju.commonitems.ItemSingle;

import java.util.logging.Logger;

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

    ItemSingle is_item;
    LinearLayout ll_content;

    private void initView() {
//        is_item = findViewById(R.id.is_item);
        ll_content = findViewById(R.id.ll_content);
//        is_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(Tag, v.getId() + "");
//            }
//        });
//        is_item.setSwitchCheckedChangeListener(new ItemSingle.OnSwitchCheckedChangeListener() {
//            @Override
//            public void onCheckedChangeListener(CompoundButton buttonView, boolean isChecked) {
//                Log.e(Tag, isChecked + "");
//            }
//        });


        ItemSingle itemSingle = new ItemSingle.Builder(mContext)
                .setLeftText("left")
                .setRightText("right")
                .setDividersType(ItemSingle.DIVIDERS_BOTH)
                .setDividerTopMarginLeft(15)
                .create();
        ll_content.addView(itemSingle);
    }
}
