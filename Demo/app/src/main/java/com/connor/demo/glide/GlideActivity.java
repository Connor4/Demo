package com.connor.demo.glide;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.connor.demo.R;

public class GlideActivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide_main);

        imageView = findViewById(R.id.image_view);
        findViewById(R.id.load_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1856787970,716725114&fm=26&gp=0.jpg";
                Glide.with(GlideActivity.this)
                        .load(url)
//                        .skipMemoryCache(true)
//                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .into(imageView);
            }
        });
    }

}
