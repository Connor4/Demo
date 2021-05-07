package com.connor.demo.glide;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.connor.demo.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    // retrofit用例
    private void retrfitTest() throws IOException {
        //创建一个非常简单的指向GitHub API的REST适配器。
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建我们的GitHub API接口的一个实例。
        GitHub github = retrofit.create(GitHub.class);
        //创建一个调用实例来查找Retrofit贡献者。
        Call<List<Contributor>> call = github.contributors("square", "retrofit");
        //获取并打印库的贡献者列表。
        Response response = call.execute();
        Log.d("dzb", response.code() + "\n"
                + response.toString() + "\n"
                + new Gson().toJson(response.body()));
    }

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    //https://api.github.com/repos/square/retrofit/contributors
    public static final String API_URL = "https://api.github.com";
    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

}
