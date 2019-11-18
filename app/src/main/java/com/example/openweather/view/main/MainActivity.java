package com.example.openweather.view.main;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.openweather.R;
import com.example.openweather.model.City;
import com.example.openweather.view.base.BaseActivity;
import com.example.openweather.view.base.BaseFragment;
import com.example.openweather.view.cityforecast.CityForecastFragment;
import com.example.openweather.view.citylist.CityListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity
        implements CityListFragment.Contract, FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getDiComponent().inject(this);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        init();

        if (savedInstanceState == null) {
            setupCityListFragment();
        }
    }

    private void init() {
        initToolbar();
    }

    private void setupCityListFragment() {
        addContentFragment(new CityListFragment());
    }

    private void setupCityForecastFragment(City city) {
        CityForecastFragment cityForecastFragment = new CityForecastFragment();
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        cityForecastFragment.setArguments(args);
        replaceContentFragment(cityForecastFragment);
    }

    private void replaceContentFragment(BaseFragment contractFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_content, contractFragment)
                .addToBackStack(null)
                .commit();
    }

    private void addContentFragment(BaseFragment contractFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_content, contractFragment)
                .commit();
    }

    private void initToolbar() {
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.app_name));
            setSupportActionBar(toolbar);
            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setDisplayShowTitleEnabled(true);
            }
            shouldDisplayHomeUp();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onCitySelected(City city) {
        setupCityForecastFragment(city);
    }

    @Override
    public void onCityAddDialogDismissed() {
        initToolbar();

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        boolean enableHomeUp = getSupportFragmentManager().getBackStackEntryCount() > 0;
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(enableHomeUp);
        }
    }
}
