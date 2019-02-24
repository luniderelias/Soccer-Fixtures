package teste.com.fixtures.Service;


import org.androidannotations.annotations.EBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import teste.com.fixtures.Model.Fixture;
import teste.com.fixtures.Model.Result;
import teste.com.fixtures.Util.RestUtil;

@EBean
public class FixtureService {

    public synchronized Observable<List<Fixture>> getFixtures()  {
        return RestUtil.api.getFixtures()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public synchronized Observable<List<Result>> getResults()  {
        return RestUtil.api.getResults()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
