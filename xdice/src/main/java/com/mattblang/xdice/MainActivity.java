package com.mattblang.xdice;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Date;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {
    int[] resources = {
            R.drawable.one_sided,
            R.drawable.two_sided,
            R.drawable.three_sided,
            R.drawable.four_sided,
            R.drawable.five_sided,
            R.drawable.six_sided
    };
    MediaPlayer player;

    Random random;
    int min = 1;
    int max = 6;

    @InjectView(R.id.table_layout) TableLayout tableLayout;
    @InjectView(R.id.button_hide) Button hide;

    @OnClick(R.id.button_roll) void roll() {
        if(player.isPlaying()) {
            player.pause();
            player.seekTo(0);
        }
        player.start();

        tableLayout.removeAllViews();
        TableRow tableRow;
        for(int i = 0; i < 3; i++) {
            tableRow = new TableRow(this);

            ImageView imageView;
            for(int j = 0; j < 3; j++) {
                int rand = random.nextInt(max - min + 1) + min;
                int index = rand - 1;
                imageView = new ImageView(this);
                imageView.setImageResource(resources[index]);
                tableRow.addView(imageView);
            }

            tableLayout.addView(tableRow);
        }
    }

    @OnClick(R.id.button_hide) void hide() {
        if(tableLayout.getVisibility() == View.VISIBLE) {
            tableLayout.setVisibility(View.INVISIBLE);
            hide.setText(R.string.button_show);
        }
        else if(tableLayout.getVisibility() == View.INVISIBLE) {
            tableLayout.setVisibility(View.VISIBLE);
            hide.setText(R.string.button_hide);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = MediaPlayer.create(this, R.raw.roll);
        random = new Random(System.currentTimeMillis());
        ButterKnife.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
