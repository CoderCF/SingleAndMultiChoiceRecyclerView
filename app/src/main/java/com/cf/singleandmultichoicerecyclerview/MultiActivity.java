package com.cf.singleandmultichoicerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MultiActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mTvCount;
    private List<String> datas;
    private List<String> selectDatas = new ArrayList<>();
    private MultiAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTvCount = (TextView) findViewById(R.id.tv_count);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MultiAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if(!mAdapter.isSelected.get(position)){
                    mAdapter.isSelected.put(position, true); // 修改map的值保存状态
                    mAdapter.notifyItemChanged(position);
                    selectDatas.add(datas.get(position));

                }else {
                    mAdapter.isSelected.put(position, false); // 修改map的值保存状态
                    mAdapter.notifyItemChanged(position);
                    selectDatas.remove(datas.get(position));
                }

                mTvCount.setText("已选中"+selectDatas.size()+"项");
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    /**
     * 全选
     * @param view
     */
    public void all(View view){
        selectDatas.clear();

        for (int i = 0; i < datas.size(); i++) {
            mAdapter.isSelected.put(i, true);
            selectDatas.add(datas.get(i));
        }
        mAdapter.notifyDataSetChanged();
        mTvCount.setText("已选中"+selectDatas.size()+"项");
    }
    /**
     * 反选
     * @param view
     */
    public void inverse(View view){
        for (int i=0; i<datas.size(); i++) {
            if(mAdapter.isSelected.get(i)){
                mAdapter.isSelected.put(i,false);
                selectDatas.remove(datas.get(i));
            } else {
                mAdapter.isSelected.put(i,true);
                selectDatas.add(datas.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
        mTvCount.setText("已选中"+selectDatas.size()+"项");

    }
    /**
     * 取消已选
     * @param view
     */
    public void cancel(View view){
        for (int i=0; i<datas.size(); i++) {
            if(mAdapter.isSelected.get(i)){
                mAdapter.isSelected.put(i,false);
                selectDatas.remove(datas.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
        mTvCount.setText("已选中"+selectDatas.size()+"项");
    }


    private void initData() {
        datas = new ArrayList<>();

        for (int i=0; i<20; i++) {
            datas.add("测试"+i);
        }
    }
}
