package com.xd.test.palettedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.xd.test.palettedemo.bean.PaletteColor;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BaseQuickAdapter<PaletteColor,BaseViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPaletteColor(R.drawable.bg1);
    }

    private void setPaletteColor(int i) {
        ImageView imageView = (ImageView) findViewById(R.id.img);
        Picasso.with(this).load(i).into(imageView);
        final List<PaletteColor> list = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                i);
        Palette.Builder p = Palette.from(bitmap);
        p.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                if (palette.getVibrantSwatch()!=null)
                    list.add(new PaletteColor(
                            palette.getVibrantSwatch().getTitleTextColor(),
                            palette.getVibrantSwatch().getBodyTextColor(),
                            palette.getVibrantSwatch().getRgb(),
                            "活力的","Vibrant"));
                if(palette.getDarkVibrantSwatch()!=null)
                    list.add(new PaletteColor(
                            palette.getDarkVibrantSwatch().getTitleTextColor(),
                            palette.getDarkVibrantSwatch().getBodyTextColor(),
                            palette.getDarkVibrantSwatch().getRgb(),
                            "活力的暗色","DarkVibrant"));
                if(palette.getLightVibrantSwatch()!=null)
                    list.add(new PaletteColor(
                            palette.getLightVibrantSwatch().getTitleTextColor(),
                            palette.getLightVibrantSwatch().getBodyTextColor(),
                            palette.getLightVibrantSwatch().getRgb(),
                            "活力的亮色","LightVibrant"));
                if(palette.getMutedSwatch()!=null)
                    list.add(new PaletteColor(
                            palette.getMutedSwatch().getTitleTextColor(),
                            palette.getMutedSwatch().getBodyTextColor(),
                            palette.getMutedSwatch().getRgb(),
                            "柔和的","Muted"));
                if(palette.getDarkMutedSwatch()!=null)
                    list.add(new PaletteColor(
                            palette.getDarkMutedSwatch().getTitleTextColor(),
                            palette.getDarkMutedSwatch().getBodyTextColor(),
                            palette.getDarkMutedSwatch().getRgb(),
                            "柔和的暗色","DarkMuted"));
                if(palette.getLightMutedSwatch()!=null)
                    list.add(new PaletteColor(
                            palette.getLightMutedSwatch().getTitleTextColor(),
                            palette.getLightMutedSwatch().getBodyTextColor(),
                            palette.getLightMutedSwatch().getRgb(),
                            "柔和的亮色","LightMuted"));
                setAdapter(list);
                setRecyclerView();
            }
        });
    }

    private void setAdapter(List<PaletteColor> list) {
        adapter = new BaseQuickAdapter<PaletteColor, BaseViewHolder>(R.layout.activity_main_item,list) {
            @Override
            protected void convert(BaseViewHolder helper, PaletteColor item) {
                helper.setText(R.id.textView2,item.getTitle()+item.getTitleInt());
                helper.setTextColor(R.id.textView,item.getTitleInt());
                helper.setTextColor(R.id.textView,item.getTvInt());
                helper.setText(R.id.textView,item.getTv()+item.getTvInt());
                ImageView imageView = helper.getView(R.id.imageView);
                imageView.setBackgroundColor(item.getImgInt());
            }
        } ;
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.damo:
                setPaletteColor(R.drawable.bg1);
                break;
            case R.id.moye:
                setPaletteColor(R.drawable.bg2);
                break;
            case R.id.nezha:
                setPaletteColor(R.drawable.bg3);
                break;
        }
        return true;
    }
}
