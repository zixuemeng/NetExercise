package com.joany.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joany.news.R;
import com.joany.news.bean.NewsEntity;

import java.util.List;

/**
 * Created by joany on 2016/9/1.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<NewsEntity> data;
    private boolean showFooter = true;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<NewsEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    public boolean isShowFooter(){
        return showFooter;
    }

    public NewsEntity getItem(int position) {
        return data != null ? data.get(position) : null;
    }
    @Override
    public int getItemViewType(int position) {
        if(!showFooter) {
            return TYPE_ITEM;
        }
        if(position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_news_list_item, parent, false);
            return new ItemViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.footer,parent,false);
            return new FooterViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            NewsEntity newsEntity = data.get(position);
            if(newsEntity != null) {
                ((ItemViewHolder) holder).title.setText(newsEntity.getTitle());
                ((ItemViewHolder) holder).description.setText(newsEntity.getDescription());
                Glide.with(context).load(newsEntity.getPicUrl()).error(R.mipmap.ic_load_fail)
                        .placeholder(R.mipmap.ic_loading).into(((ItemViewHolder) holder).newsImg);
            }
        }
    }

    @Override
    public int getItemCount() {
        int footer = showFooter ? 1 : 0;
        return data != null ? data.size()+ footer : footer;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView description;
        ImageView newsImg;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_tv);
            description = (TextView) itemView.findViewById(R.id.title_des_tv);
            newsImg = (ImageView) itemView.findViewById(R.id.news_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onItemClickListener != null) {
                onItemClickListener.onItemClick(view,this.getPosition());
            }
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
