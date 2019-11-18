package com.example.openweather.view.citylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openweather.R;
import com.example.openweather.model.City;
import com.example.openweather.util.ViewFlipperUtil;
import com.example.openweather.view.base.ContractFragment;
import com.example.openweather.view.cityadd.CityAddDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListFragment extends ContractFragment<CityListFragment.Contract> implements CityListContract.View {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.viewflipper)
    ViewFlipper viewFlipper;

    @Inject
    CityListPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDiComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        setupUi();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void setupRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        CityListAdapter adapter = new CityListAdapter(
                new ArrayList<>(),
                city -> getContract().onCitySelected(city));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteCity(((CityListAdapter) recyclerView.getAdapter()).getItem(viewHolder.getAdapterPosition()));
                ((CityListAdapter) recyclerView.getAdapter()).delete(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setupUi() {
        fab.setOnClickListener(view -> {
            FragmentManager fragmentManager = getFragmentManager();
            CityAddDialogFragment cityAddFragment = new CityAddDialogFragment();
            cityAddFragment.setTargetFragment(this, CityAddDialogFragment.REQUEST_CODE);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(android.R.id.content, cityAddFragment).addToBackStack(null).commit();
        });

        setupRecyclerView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CityAddDialogFragment.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.loadData();
            }

            getContract().onCityAddDialogDismissed();
        }
    }

    @Override
    public void showLoadingLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, progressBar);
    }

    @Override
    public void showContentLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, recyclerView);
    }

    @Override
    public void showErrorLayout() {
        ViewFlipperUtil.setDisplayedChild(viewFlipper, errorLayout);
    }

    @Override
    public void updateData(List<City> cityList) {
        ((CityListAdapter) recyclerView.getAdapter()).replaceDataSet(cityList);
    }

    public interface Contract {

        void onCitySelected(City city);

        void onCityAddDialogDismissed();

    }

}
