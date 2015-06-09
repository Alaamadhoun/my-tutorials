package com.alaapack.modernartui_java2tut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private int[] originalColors = {R.color.yellow, R.color.white,
            R.color.blue, R.color.red, R.color.white};
    //    private int[] actualColors = new int[originalColors.length];
    private String TAG = "bisio";
    private ArrayList<TextView> blockList = null;
    private float[] hsv = new float[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mondrian);
        blockList = new ArrayList<>();
        TextView block1 = (TextView) findViewById(R.id.block1);
        TextView block2 = (TextView) findViewById(R.id.block2);
        TextView block3 = (TextView) findViewById(R.id.block3);
        TextView block4 = (TextView) findViewById(R.id.block4);
        TextView block5 = (TextView) findViewById(R.id.block5);
        blockList.add(block1);
        blockList.add(block2);
        blockList.add(block3);
        blockList.add(block4);
        blockList.add(block5);
        initColors();
        logSaturations();
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                               @Override
                                               public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                               }

                                               @Override
                                               public void onStartTrackingTouch(SeekBar seekBar) {

                                               }

                                               @Override
                                               public void onStopTrackingTouch(SeekBar seekBar) {
                                                   float factor = (float) (100 - seekBar.getProgress()) / 100;
                                                   doRepaint(factor);
                                               }
                                           }
        );
    }

    public void doRepaint(float factor) {
        for (int i = 0; i < blockList.size(); i++) {
            int color = getResources().getColor(originalColors[i]);
            Color.colorToHSV(color, hsv);
            hsv[1] = hsv[1] * factor;
            int new_color = Color.HSVToColor(hsv);
            blockList.get(i).setBackgroundColor(new_color);
        }
    }


    public void initColors() {
        for (int i = 0; i < blockList.size(); i++) {
            int color = getResources().getColor(originalColors[i]);
            blockList.get(i).setBackgroundColor(color);
        }
    }

    public void logSaturations() {

        for (int i = 0; i < blockList.size(); i++) {
            int color = getResources().getColor(originalColors[i]);
            Color.colorToHSV(color, hsv);
            float saturation = hsv[1];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mondrian, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_more_information) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title);

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String url = "https://github.com/Java2Group";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                }
            });

            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });


            AlertDialog dialog = builder.create();

            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
