package org.rogotulka.rozhkovaanna.ui;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.rogotulka.rozhkovaanna.NewsApplication;
import org.rogotulka.rozhkovaanna.R;
import org.rogotulka.rozhkovaanna.data.News;
import org.rogotulka.rozhkovaanna.data.Source;
import org.rogotulka.rozhkovaanna.logic.Logic;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks {

    private static final int LOADER_NEWS = 0;

    private ListView vListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vListView = (ListView) findViewById(R.id.news_list);
        getLoaderManager().initLoader(LOADER_NEWS, null, this).forceLoad();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.news_refresh) {
            getLoaderManager().restartLoader(LOADER_NEWS, null, MainActivity.this).forceLoad();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.findItem(R.id.news_refresh).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                getLoaderManager().restartLoader(LOADER_NEWS, null, MainActivity.this).forceLoad();
//                return true;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == LOADER_NEWS) {
            return new AsyncTaskLoader<List<News>>(getApplicationContext()) {
                @Override
                public List<News> loadInBackground() {
                    Logic logic = ((NewsApplication) getApplication()).getLogic();
                    return logic.getCompoundNews(Arrays.asList(Source.values()));
                }
            };
        }
        throw new IllegalStateException("Unknown loader");
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onLoadFinished(Loader loader, Object data) {
        if (data != null) {
            List<News> newsList = (List<News>) data;
            vListView.setAdapter(new NewsAdapter(this, newsList));
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        // NOP
    }
}
