package com.spk.ahp_lokasipabrikbambu.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.spk.ahp_lokasipabrikbambu.R;
import com.spk.ahp_lokasipabrikbambu.input.KriteriaActivity;
import com.spk.ahp_lokasipabrikbambu.model.KeputusanViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wisnu on 12/23/17.
 */

public class AlternatifBobotResultActivity extends AppCompatActivity {

    private KeputusanViewModel keputusanViewModel;

    private LinearLayout alternatifBobotResultWrapper;
    private LinearLayout backToHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternatif_bobot_result);

        bindData();
        initToolbar();
        initView();
        initBackToHomeButton();
    }

    private void bindData() {
        keputusanViewModel = (KeputusanViewModel) getIntent().getSerializableExtra(KeputusanViewModel.DATA_KEPUTUSAN_KEY);
    }

    private void initToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        alternatifBobotResultWrapper = findViewById(R.id.alternatif_result_wrapper);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TableLayout table = new TableLayout(this);
        table.setPadding(2, 2, 2, 10);

        Iterator<String> it = keputusanViewModel.alternatifKeGradeMap.keySet().iterator();
        List<TableRow> descendingOptions = new ArrayList<TableRow>();

        while (it.hasNext()) {
            TableRow row = new TableRow(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            String alternatif = it.next();
            Float grade = keputusanViewModel.alternatifKeGradeMap.get(alternatif);
            String percent = String.valueOf(Math.round(grade));

            TextView optionLabel = new TextView(this);
            optionLabel.setTextSize(24);
            optionLabel.setText(alternatif + ": ");
            optionLabel.setGravity(Gravity.START);
            optionLabel.setPadding(5, 5, 5, 5);

            TextView optionGrade = new TextView(this);
            optionGrade.setTextSize(24);
            optionGrade.setText(percent + "%");
            optionGrade.setGravity(Gravity.START);
            optionGrade.setPadding(5, 5, 5, 5);

            row.addView(optionLabel);
            row.addView(optionGrade);
            row.setTag(grade);

            if (descendingOptions.isEmpty()) {
                descendingOptions.add(row);
            } else {
                boolean inserted = false;
                for (int i = 0; i < descendingOptions.size(); i++) {
                    if (((Float) descendingOptions.get(i).getTag()) < grade) {
                        descendingOptions.add(i, row);
                        inserted = true;
                        break;
                    }
                }
                if (!inserted) {
                    descendingOptions.add(row);
                }
            }

        }

        for (TableRow option : descendingOptions) {
            table.addView(option);
        }
        layout.addView(table);

        ScrollView scroller = new ScrollView(this);
        scroller.addView(layout);
        alternatifBobotResultWrapper.addView(scroller);
    }

    private void initBackToHomeButton() {
        backToHomeButton = findViewById(R.id.back_to_first_btn);
        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeActivity();
                finish();
            }
        });
    }

    private void launchHomeActivity() {
        Intent intent = new Intent(this, KriteriaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
