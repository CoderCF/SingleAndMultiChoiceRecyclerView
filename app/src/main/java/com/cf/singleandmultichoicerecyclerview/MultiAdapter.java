package com.cf.singleandmultichoicerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * 描    述：
 * 创建日期：2017/7/17 16:01
 * 作    者：Chengfu
 * 邮    箱：
 * 备    注：
 */
public class MultiAdapter extends RecyclerView.Adapter {

    private List<String> datas;
    public static HashMap<Integer, Boolean> isSelected;

    public MultiAdapter(List<String> datas) {
        this.datas = datas;
        init();
    }

    // 初始化 设置所有item都为未选择
    public void init() {
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < datas.size(); i++) {
            isSelected.put(i, false);
        }
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new MultiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MultiViewHolder){
                final MultiViewHolder viewHolder = (MultiViewHolder) holder;
                String name = datas.get(position);
                viewHolder.mTvName.setText(name);
                viewHolder.mCheckBox.setChecked(isSelected.get(position));
                viewHolder.itemView.setSelected(isSelected.get(position));

                if (mOnItemClickLitener != null)
                {
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            mOnItemClickLitener.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition());
                        }
                    });
                }
            }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MultiViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName;
        CheckBox mCheckBox;

        public MultiViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

}
