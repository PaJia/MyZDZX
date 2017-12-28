package com.example.mjj.daytopnewschangetabs.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mjj.daytopnewschangetabs.R;
import com.example.mjj.daytopnewschangetabs.bean.HeadlineBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/11/27.
 */
public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.ViewHelder> implements View.OnClickListener {
    private Context context;
    private ArrayList<HeadlineBean.DataBean> mList;

    public HeadlineAdapter(Context context, ArrayList<HeadlineBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;
    }



    @Override
    public ViewHelder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fragment_headline_item,parent,false);
        ViewHelder viewHelder=new ViewHelder(view);
        view.setOnClickListener(this);
        return viewHelder;
    }

    @Override
    public void onBindViewHolder(ViewHelder holder, int position) {
        holder.mTilte.setText(mList.get(position).getTitle());
        holder.mNr.setText(mList.get(position).getDescription());
        Glide.with(context).load(mList.get(position).getThumb()).into(holder.mImage);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mList.isEmpty()?0:mList.size();
    }

    public class ViewHelder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mTilte,mNr;
        public ViewHelder(View itemView) {
            super(itemView);
            mImage= (ImageView) itemView.findViewById(R.id.Headline_image);
            mTilte= (TextView) itemView.findViewById(R.id.Headline_title);
            mNr= (TextView) itemView.findViewById(R.id.Headline_nr);
        }
    }

    public interface OnClickItem {
        void setClick(View v, int position);
    }

    private OnClickItem item=null;

    @Override
    public void onClick(View v) {
        if (item != null) {
            item.setClick(v, (int) v.getTag());
        }
    }

    public void setOnClick(OnClickItem item) {
        this.item = item;
    }
}
