package org.rogotulka.rozhkovaanna.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.rogotulka.rozhkovaanna.R;
import org.rogotulka.rozhkovaanna.data.News;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

public class NewsAdapter extends BaseAdapter {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private List<News> mNewsList;
    private Context mContext;

    private static class ViewHolder {
        private ImageView vNewsImage;
        private TextView vTitle;
        private TextView vDescription;
        private TextView vDate;
        private TextView vSource;
        private DownloadImageTask mDownloadImageTask;
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        private ImageView vImageView;

        public DownloadImageTask(ImageView imageView) {
            vImageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new URL(url).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                //NOP
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            vImageView.setImageBitmap(result);
        }
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.vNewsImage = (ImageView) convertView.findViewById(R.id.news_image);
            viewHolder.vTitle = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.vDescription = (TextView) convertView.findViewById(R.id.news_description);
            viewHolder.vDate = (TextView) convertView.findViewById(R.id.news_date);
            viewHolder.vSource = (TextView) convertView.findViewById(R.id.news_source);
            View root = convertView.findViewById(R.id.root);
            // TODO: List старается переиспользовать элементы. Подозреваю, что при таком подходе у вас будут новости пропадать из списка и расти потребление памяти. Попробуйте загрузить 40 новостей и удалить с 5-ой по 15-ую. После этого промотать лист в самый конец. Если все новости по порядку будут показаны, то все ок. Если будут "дырки", то ваш подход не работает.
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.vDescription.getVisibility() == View.VISIBLE) {
                        viewHolder.vDescription.setVisibility(View.GONE);
                    } else {
                        viewHolder.vDescription.setVisibility(View.VISIBLE);
                    }
                }
            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.vNewsImage.setImageDrawable(null);
            viewHolder.vDescription.setVisibility(View.GONE);
            if (viewHolder.mDownloadImageTask != null) {
                viewHolder.mDownloadImageTask.cancel(true);
            }
        }

        News news = mNewsList.get(position);
        viewHolder.vTitle.setText(news.getTitle());
        viewHolder.vDescription.setText(news.getDescription());
        viewHolder.vDate.setText(FORMATTER.format(news.getDate()));
        viewHolder.vSource.setText(news.getSource().getName());
        if (news.getImage() != null) {
            viewHolder.vNewsImage.setVisibility(View.VISIBLE);
            viewHolder.mDownloadImageTask = new DownloadImageTask(viewHolder.vNewsImage);
            viewHolder.mDownloadImageTask.execute(news.getImage());
        } else {
            viewHolder.vNewsImage.setVisibility(View.GONE);
        }

        return convertView;
    }
}
