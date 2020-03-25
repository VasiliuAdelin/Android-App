package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import java.util.List;

public class activity_preference extends PreferenceActivity {

    public class MyPreferenceActivity extends PreferenceActivity {
        @Override
        public void onBuildHeaders(List<Header> target) {
            loadHeadersFromResource(R.xml.headers_preference, target);
        }

        @Override
        protected boolean isValidFragment(String fragmentName) {
            return preference_fragment.class.getName().equals(fragmentName);
        }
    }
}