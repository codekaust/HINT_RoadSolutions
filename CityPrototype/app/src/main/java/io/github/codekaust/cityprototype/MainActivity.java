package io.github.codekaust.cityprototype;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.github.codekaust.cityprototype.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private CityMatrix cityMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        cityMatrix = new CityMatrix();

        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setMatrix(cityMatrix.getMatrix());

        Button btn = findViewById(R.id.btn_update);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i1, j1, i2, j2;
                EditText editi1 = findViewById(R.id.et_start_i);
                EditText editi2 = findViewById(R.id.et_end_i);
                EditText editj1 = findViewById(R.id.et_start_j);
                EditText editj2 = findViewById(R.id.et_end_j);

                i1 = Integer.parseInt(editi1.getText().toString());
                j1 = Integer.parseInt(editj1.getText().toString());
                i2 = Integer.parseInt(editi2.getText().toString());
                j2 = Integer.parseInt(editj2.getText().toString());

                int m = Math.abs(j2 - j1 + 1);
                int n = Math.abs(i2 - i1 + 1);

                Path[] paths = new Path[fact(m + n) / (fact(m) * fact(n))];
                for (int i = 0; i < paths.length; i++) {
                    paths[i] = new Path((m + n - 1));
                }

                paths = pathFinder(cityMatrix.getMatrix(), i1, j1, i2, j2);

                for (int i = 0; i < paths.length; i++) {
                    paths[i].weightCalculator();
                }

                int minWeightedPath = 0, minWeight = paths[0].weightTotal;

                for (int i = 0; i < paths.length; i++) {
                    if (minWeight > paths[i].weightTotal) {
                        minWeight = paths[i].weightTotal;
                        minWeightedPath = i;
                    }
                }

                for(int i=0; i<5; i++){
                    for(int j =0; j<4; j++){
                        cityMatrix.matrix[i][j].g1 = false;
                        cityMatrix.matrix[i][j].g2 = false;
                        cityMatrix.matrix[i][j].g3 = false;
                        cityMatrix.matrix[i][j].g4 = false;
                    }
                }

                for (int i = 1; i < paths[minWeightedPath].pathMembers.length; i++) {
                    if (paths[minWeightedPath].pathMembers[i].i < paths[minWeightedPath].pathMembers[i - 1].i) {
                        cityMatrix.matrix[paths[minWeightedPath].pathMembers[i].i][paths[minWeightedPath].pathMembers[i].j].g2 = true;
                    }
                    if (paths[minWeightedPath].pathMembers[i].i > paths[minWeightedPath].pathMembers[i - 1].i) {
                        cityMatrix.matrix[paths[minWeightedPath].pathMembers[i].i][paths[minWeightedPath].pathMembers[i].j].g1 = true;
                    }
                    if (paths[minWeightedPath].pathMembers[i].j < paths[minWeightedPath].pathMembers[i - 1].j) {
                        cityMatrix.matrix[paths[minWeightedPath].pathMembers[i].i][paths[minWeightedPath].pathMembers[i].j].g3 = true;
                    }
                    if (paths[minWeightedPath].pathMembers[i].j > paths[minWeightedPath].pathMembers[i - 1].j) {
                        cityMatrix.matrix[paths[minWeightedPath].pathMembers[i].i][paths[minWeightedPath].pathMembers[i].j].g4 = true;
                    }
                }

                binding.setMatrix(cityMatrix.getMatrix());

                for(int i=0; i<paths[minWeightedPath].pathMembers.length;i++){
                    Toast.makeText(MainActivity.this,String.valueOf(paths[minWeightedPath].pathMembers[i].i)+String.valueOf(paths[minWeightedPath].pathMembers[i].j) ,Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public Path[] pathFinder(Intersection matrix[][], int i1, int j1, int i2, int j2) {
        int m = Math.abs(j2 - j1 + 1);
        int n = Math.abs(i2 - i1 + 1);

        int addj = -1, addi = -1;
        if (j2 > j1) {
            addj = 1;
        }
        if (i2 > i1) {
            addi = 1;
        }

        Path[] paths = new Path[fact(m + n) / (fact(m) * fact(n))];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = new Path((m + n - 1));
            paths[i].pathMembers[0] = matrix[i1][j1];
        }

        Path paths1[], paths2[];

        int p = 0;

        if (addi * (i1 + addi) <= addi * i2) {
            paths1 = pathFinder(matrix, i1 + addi, j1, i2, j2);
            for (int i = 0; i < paths1.length; i++) {
                for (int j = 0; j < paths1[i].pathMembers.length; j++) {
                    paths[p].pathMembers[j + 1] = paths1[i].pathMembers[j];
                }
                p++;
            }
        }

        if (addj * (j1 + addj) <= addj * j2) {
            paths2 = pathFinder(matrix, i1, j1 + addj, i2, j2);
            for (int i = 0; i < paths2.length; i++) {
                for (int j = 0; j < paths2[i].pathMembers.length; j++) {
                    paths[p].pathMembers[j + 1] = paths2[i].pathMembers[j];
                }
                p++;
            }
        }

        return paths;
    }

    public int fact(int a) {
        int fact = 1;
        for (int i = 1; i <= a; i++) {
            fact *= i;
        }

        return fact;
    }
}