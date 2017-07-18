package com.cf.singleandmultichoicerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * 描    述：
 * 创建日期：2017/7/17 16:01
 * 作    者：Chengfu
 * 邮    箱：
 * 备    注：
 */
public class SingleAdapter extends RecyclerView.Adapter {

    private List<String> datas;

    private int selected = -1;
    public SingleAdapter(List<String> datas) {
        this.datas = datas;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void setSelection(int position){
        this.selected = position;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof SingleViewHolder){
            final SingleViewHolder viewHolder = (SingleViewHolder) holder;
            String name = datas.get(position);
            viewHolder.mTvName.setText(name);

            if(selected == position){
                viewHolder.mCheckBox.setChecked(true);
                viewHolder.itemView.setSelected(true);
            } else {
                viewHolder.mCheckBox.setChecked(false);
                viewHolder.itemView.setSelected(false);
            }

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

    class SingleViewHolder extends RecyclerView.ViewHolder{
        TextView mTvName;
        CheckBox mCheckBox;

        public SingleViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

}
