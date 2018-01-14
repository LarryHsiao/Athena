package com.silverhetch.athena.ui.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;

import com.silverhetch.athena.R;
import com.silverhetch.athena.review.NotificationReviewImpl;

import static android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences;

/**
 * Created by mikes on 1/13/2018.
 */

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.setting_title);
        getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static Fragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        final String keyNotificationReview = getString(R.string.key_notificationReview);
        if (keyNotificationReview.equals(key)){
            final boolean reviewEnable = sharedPreferences.getBoolean(keyNotificationReview,false);
            NotificationReviewImpl review = new NotificationReviewImpl(getContext());
            if (reviewEnable) {
                review.schedule();
            }else {
                review.cancelAll();
            }
        }
    }
}
