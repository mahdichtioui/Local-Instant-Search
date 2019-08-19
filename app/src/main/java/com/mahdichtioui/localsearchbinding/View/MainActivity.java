package com.mahdichtioui.localsearchbinding.View;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.mahdichtioui.localsearchbinding.Model.DataManager.MoodDataManager;
import com.mahdichtioui.localsearchbinding.Model.Entity.Mood;
import com.mahdichtioui.localsearchbinding.R;
import com.mahdichtioui.localsearchbinding.Utils.RecyclerTouchListener;
import com.mahdichtioui.localsearchbinding.View.Adapter.MoodAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_moods)
    RecyclerView rvMoods;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.input_search)
    EditText inputSearch;

    private Unbinder unbinder;
    private MoodAdapter adapter;
    private MoodDataManager dataManager;
    private List<Mood> lstMoods = new ArrayList<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_mood_white);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Moody Guess");

        dataManager = new MoodDataManager();

        initView();
        setupRecyclerViewClickListener(rvMoods, lstMoods);


        disposable.add(RxTextView.textChangeEvents(inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchMoodsTextWatcher()));

    }

    private DisposableObserver<TextViewTextChangeEvent> searchMoodsTextWatcher() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                filter(textViewTextChangeEvent.text().toString().toLowerCase());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void initView() {
        rvMoods.setLayoutManager(new LinearLayoutManager(this));
        rvMoods.setHasFixedSize(true);

        lstMoods.addAll(dataManager.initValues());

        adapter = new MoodAdapter(this, lstMoods);
        rvMoods.setAdapter(adapter);
    }

    private void setupRecyclerViewClickListener(RecyclerView rv, List<Mood> lst){
        rv.addOnItemTouchListener(new RecyclerTouchListener(this, rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Mood mood = lst.get(position);
                Toast.makeText(MainActivity.this, "You're feeling "+mood.getSentiment()+" Today!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void filter(String inputText){
        List<Mood> lst = new ArrayList<>();

        for (Mood mood : lstMoods){
            String sentiment = mood.getSentiment();

            if(sentiment.toLowerCase().contains(inputText)){
                lst.add(mood);
            }
        }

        adapter.filter(lst);
    }

    @Override
    protected void onDestroy() {
        disposable.clear();
        unbinder.unbind();
        super.onDestroy();
    }
}
