package com.example.openweather.view.base;

import android.os.Bundle;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.openweather.MainApplication;
import com.example.openweather.dagger.DiComponent;

import butterknife.ButterKnife;

public abstract class BaseDialogFragment extends DialogFragment {

    private MainApplication getMainApplication() {
        return (MainApplication) getActivity().getApplication();
    }

    protected DiComponent getDiComponent() {
        return getMainApplication().getDiComponent();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
}
