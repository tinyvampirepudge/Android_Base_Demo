package com.tiny.demo.firstlinecode.templates.template5.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

public class MvpTest5Actiity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_test5_actiity);
        initFrag();
    }

    private void initFrag() {
        Bundle bundle = new Bundle();
        bundle.putString("txt", MvpTest5Actiity.class.getSimpleName());
        MvpTest5Fragment fragment = MvpTest5Fragment.newInstance(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
