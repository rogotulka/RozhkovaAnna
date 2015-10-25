package org.rogotulka.rozhkovaanna;

import android.app.Application;

import org.rogotulka.rozhkovaanna.logic.Logic;
import org.rogotulka.rozhkovaanna.logic.LogicProvider;

public class NewsApplication extends Application {

    private Logic mLogic;

    public Logic getLogic() {
        return mLogic;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLogic = LogicProvider.getLogic();
    }
}
