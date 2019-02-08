package com.example.desk.ui.room;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.desk.R;
import com.example.desk.adapter.DeskAdapter;
import com.example.desk.entity.Desk;
import com.example.desk.mvp.MVPBaseActivity;
import com.example.qrcode.Constant;
import com.example.qrcode.ScannerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RoomActivity extends MVPBaseActivity<RoomContract.View, RoomPresenter> implements RoomContract.View,DeskAdapter.Two {
    private static final int RESULT_REQUEST_CODE = 2;
    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<Desk> deskList = new ArrayList<>();
    private Desk tempdesk;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String position = intent.getStringExtra("roomid");
        deskList = mPresenter.getDataTwo(position);
        Glide.with(RoomActivity.this).load(R.mipmap.welcome).into(ivWelcome);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(position);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager
                (6,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DeskAdapter adapter = new DeskAdapter(RoomActivity.this, deskList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void RequestDesk(Desk desk) {
        mPresenter.getDataThree(desk);
    }
    //当前座位正在被他人使用
    @Override
    public void ErrorOne(String error) {
        Toast.makeText(RoomActivity.this,error,Toast.LENGTH_SHORT).show();
    }
    //当前座位处于暂离状态，不能被你使用
    @Override
    public void ErrorTwo(String error) {
        Toast.makeText(RoomActivity.this,error,Toast.LENGTH_SHORT).show();
    }
    //未知错误
    @Override
    public void ErrorThree(String error) {
        Toast.makeText(RoomActivity.this,error,Toast.LENGTH_SHORT).show();
    }
    //该座位可以被抢，扫描二维码之后开抢
    @Override
    public void Success(Desk desk) {
        //TODO:可以抢该座位,通过扫描二维码再抢一次
        tempdesk = desk;
        Intent intent = new Intent(RoomActivity.this,ScannerActivity.class);
        startActivityForResult(intent,RESULT_REQUEST_CODE);
    }
    //成功抢到该座位
    @Override
    public void Success2(String info) {
        Toast.makeText(RoomActivity.this,info,Toast.LENGTH_SHORT).show();
        //onRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case RESULT_REQUEST_CODE:
                    if (data == null){
                        return;
                    }
                    String content = data.getStringExtra(Constant.EXTRA_RESULT_CONTENT);//得到扫描的二维码data
                    mPresenter.QiangZuo(content,tempdesk);//根据二维码data，具体desk data抢座位
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}