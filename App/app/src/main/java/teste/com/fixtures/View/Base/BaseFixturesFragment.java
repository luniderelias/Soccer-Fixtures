package teste.com.fixtures.View.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import teste.com.fixtures.Presenter.FixtureFragment.FixturesPresenter;
import teste.com.fixtures.Presenter.FixtureFragment.IFixturesPresenter;
import teste.com.fixtures.R;
import teste.com.fixtures.Service.FixtureService;
import teste.com.fixtures.Util.ConnectivityUtil;

import static teste.com.fixtures.View.MainActivity.sortTerm;

public class BaseFixturesFragment extends Fragment implements IBaseFixturesView {

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.monthYear)
    public TextView monthYear;

    @BindView(R.id.loadingImageView)
    ImageView loadingImageView;


    @BindView(R.id.searchEditText)
    EditText searchEditText;

    public IFixturesPresenter fixturesPresenter;

    public FixtureService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixtures, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fixturesPresenter = new FixturesPresenter(this);
        service = new FixtureService();
        showLoading();
        configureRecyclerView();
        setAdapter();
        checkConnectivity();
        getData();
        setScrollListener();
        onTextChange();
    }

    @Override
    public void showLoading() {
        Glide.with(getActivity())
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .into(loadingImageView);
        setLoadingVisibility(View.VISIBLE);
    }

    @Override
    public void configureRecyclerView() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void checkConnectivity() {
        if (!ConnectivityUtil.hasNetworkConnection(getActivity()))
            Snackbar.make(recyclerView,
                    getString(R.string.not_connected),
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.connect, view -> startActivityForResult(new Intent(
                            android.provider.Settings.ACTION_SETTINGS), 0))
                    .setActionTextColor(getResources()
                            .getColor(R.color.light_blue)).show();
    }

    @Override
    public void showTryAgainSnackbar() {
        Snackbar.make(recyclerView,
                getString(R.string.connection_failed),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again, view -> getData())
                .setActionTextColor(getResources()
                        .getColor(R.color.light_blue)).show();
    }


    @Override
    public void setLoadingVisibility(int visibility) {
        loadingImageView.setVisibility(visibility);
    }


    @Override
    public void onTextChange() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sortTerm = charSequence.toString();
                updateList();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    @Override
    public void setAdapter() { /*Should be Implemented on each Child Fragment */ }

    @Override
    public void getData() { /*Should be Implemented on each Child Fragment */ }

    @Override
    public void updateList() { /*Should be Implemented on each Child Fragment */ }

    @Override
    public void sortItems() {/*Should be Implemented on each Child Fragment */ }

    @Override
    public void setScrollListener() { /*Should be Implemented on each Child Fragment */ }
}