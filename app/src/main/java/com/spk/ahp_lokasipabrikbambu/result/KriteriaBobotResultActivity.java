package com.spk.ahp_lokasipabrikbambu.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.spk.ahp_lokasipabrikbambu.R;
import com.spk.ahp_lokasipabrikbambu.bobot.AlternatifBobotActivity;
import com.spk.ahp_lokasipabrikbambu.model.KeputusanViewModel;

/**
 * Created by wisnu on 12/23/17.
 */

public class KriteriaBobotResultActivity extends AppCompatActivity {

    private LinearLayout okButton;
    private LinearLayout kriteriaResultContainer;

    private KeputusanViewModel keputusanViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kriteria_bobot_result);

        bindData();
        initToolbar();
        initResultView();
        initOkButton();
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void bindData() {
        keputusanViewModel = (KeputusanViewModel) getIntent().getSerializableExtra(KeputusanViewModel.DATA_KEPUTUSAN_KEY);
    }

    @SuppressLint("SetTextI18n")
    private void initResultView() {
        kriteriaResultContainer = findViewById(R.id.kriteria_result_container);

        TableLayout tabel = new TableLayout(this);
        tabel.setPadding(2, 2, 2, 10);
        for (String kriteria : keputusanViewModel.kriteriaKeBeratMap.keySet()) {

            TableRow baris = new TableRow(this);
            baris.setOrientation(LinearLayout.HORIZONTAL);

            Float weight = keputusanViewModel.kriteriaKeBeratMap.get(kriteria);
            String persen = String.valueOf(Math.round(weight * 100));

            TextView kriteriaLabel = new TextView(this);
            kriteriaLabel.setText(kriteria + ": ");
            kriteriaLabel.setGravity(Gravity.END);
            kriteriaLabel.setPadding(5, 5, 5, 5);
            kriteriaLabel.setTextSize(24.0f);

            TextView kriteriaBobot = new TextView(this);
            kriteriaBobot.setText(persen + "%");
            kriteriaBobot.setGravity(Gravity.START);
            kriteriaBobot.setPadding(5, 5, 5, 5);
            kriteriaBobot.setTextSize(24.0f);

            baris.addView(kriteriaLabel);
            baris.addView(kriteriaBobot);

            tabel.addView(baris);
        }
        kriteriaResultContainer.addView(tabel);
    }

    private void initOkButton() {
        okButton = findViewById(R.id.ok_btn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAlternatifBobotScreen();
            }
        });
    }

    private void launchAlternatifBobotScreen() {
        Intent intent = new Intent(this, AlternatifBobotActivity.class);
        intent.putExtra(KeputusanViewModel.DATA_KEPUTUSAN_KEY, keputusanViewModel);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
