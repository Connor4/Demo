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
                String url = "http://g.hiphotos.baidu.com/image/pic/item/e61190ef76c6a7efa0938dc7f3faaf51f2de669d.jpg";
                Glide.with(GlideActivity.this).load(url).into(imageView);
            }
        });
    }

}
