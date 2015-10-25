package org.rogotulka.rozhkovaanna.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.rogotulka.rozhkovaanna.R;
import org.rogotulka.rozhkovaanna.data.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private List<News> mNewsList;
    private Context mContext;

    private static class ViewHolder {
        private ImageView vNewsImage;
        private TextView vTitle;
    }

    public NewsAdapter(Context context, List<News> newsList) {
        if (newsList == null) {
            throw new IllegalArgumentException("News List must not be null");
        }
        mContext = context;
        mNewsList = newsList;
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.vNewsImage = (ImageView) convertView.findViewById(R.id.news_image);
            viewHolder.vTitle = (TextView) convertView.findViewById(R.id.news_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.vNewsImage.setImageDrawable(null);
        }

        News news = mNewsList.get(position);
        viewHolder.vTitle.setText(news.getTitle());

        return convertView;
    }
}
