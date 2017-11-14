package com.example.yls.newsclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yls.newsclient.NewsEntity;
import com.example.yls.newsclient.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class NewsAdapter extends BaseAdapter {

    private static final int ITEM_TYPE_WITH_1_IMAGE = 0;
    private static final int ITEM_TYPE_WITH_3_IMAGE = 1;

    private Context mContext;
    private List<NewsEntity.ResultBean> mResultBeen;

    public NewsAdapter(Context context, List<NewsEntity.ResultBean> resultBeen) {
        this.mContext = context;
        this.mResultBeen = resultBeen;
    }

    @Override
    public int getCount() {
        return (mResultBeen == null) ? 0 : mResultBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity.ResultBean info = (NewsEntity.ResultBean) getItem(position);

        int itemViewType = getItemViewType(position);
        //列表的优化
        /**
         * viewHolder优化 findViewById浪费时间  以空间换时间
         * convertView优化  没有时才创建
         */
        if (itemViewType == ITEM_TYPE_WITH_1_IMAGE) {

            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_news_1, null);
            }
            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.id_item01_iv);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.id_item01_tv_title);
            TextView tvSource = (TextView) convertView.findViewById(R.id.id_item01_source);
            TextView tvComment = (TextView) convertView.findViewById(R.id.id_item01_comment);

            tvTitle.setText(info.getTitle());
            tvSource.setText(info.getSource());
            tvComment.setText(info.getReplyCount() + "跟帖");
            Picasso.with(mContext).load(info.getImgsrc()).into(ivIcon);
        } else if (itemViewType == ITEM_TYPE_WITH_3_IMAGE) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_news_2, null);
            }

            TextView tvTitle = (TextView) convertView.findViewById(R.id.id_item02_tv);
            TextView tvComment = (TextView) convertView.findViewById(R.id.id_item02_comment);
            ImageView iv01 = (ImageView) convertView.findViewById(R.id.id_item02_iv01);
            ImageView iv02 = (ImageView) convertView.findViewById(R.id.id_item02_iv02);
            ImageView iv03 = (ImageView) convertView.findViewById(R.id.id_item02_iv03);

            tvTitle.setText(info.getTitle());
            tvComment.setText(info.getReplyCount() + "跟帖");
            try {

                Picasso.with(mContext).load(info.getImgsrc()).into(iv01);
                Picasso.with(mContext).load(info.getImgextra().get(0).getImgsrc()).into(iv02);
                Picasso.with(mContext).load(info.getImgextra().get(1).getImgsrc()).into(iv03);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {

        NewsEntity.ResultBean item = (NewsEntity.ResultBean) getItem(position);
        if (item.getImgextra() == null || item.getImgextra().size() == 0) {
            return ITEM_TYPE_WITH_1_IMAGE;
        } else {
            return ITEM_TYPE_WITH_3_IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 重置列表的所有的数据，并刷新列表显示
     * @param listDatas
     */
    public void setDatas(List<NewsEntity.ResultBean> listDatas) {
        this.mResultBeen = listDatas;
        notifyDataSetChanged();     // 刷新列表
    }

    /** 追加数据，并刷新列表显示 */
    public void appendDatas(List<NewsEntity.ResultBean> listDatas) {
        this.mResultBeen.addAll(listDatas);
        notifyDataSetChanged();     // 刷新列表
    }
}
