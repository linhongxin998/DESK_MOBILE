package com.example.desk.ui.myinfo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.desk.R;
import com.example.desk.entity.User;
import com.example.desk.mvp.MVPBaseActivity;
import com.example.desk.view.ItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class MyinfoActivity extends MVPBaseActivity<MyinfoContract.View, MyinfoPresenter> implements MyinfoContract.View {
    @BindView(R.id.h_back)
    ImageView hBack;
    @BindView(R.id.h_head)
    ImageView hHead;
    @BindView(R.id.user_line)
    ImageView userLine;
    @BindView(R.id.user_uid)
    TextView userUid;
    @BindView(R.id.iv_college)
    ItemView ivCollege;
    @BindView(R.id.iv_sex)
    ItemView ivSex;
    @BindView(R.id.iv_class)
    ItemView ivClass;
    @BindView(R.id.iv_email)
    ItemView ivEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        //设置背景磨砂效果
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(hBack);
        //设置圆形图像
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(hHead);
        /*
        User.DataBean databean = User.getInstance().getData();
        ivCollege.setRightDesc(databean.getCollege());
        ivClass.setRightDesc(databean.getClassss());
        ivSex.setRightDesc(databean.getGender());
        ivEmail.setRightDesc(databean.getEmail());
        userUid.setText(databean.getUserid());
        */
    }
}
