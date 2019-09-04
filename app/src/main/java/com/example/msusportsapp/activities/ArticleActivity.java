package com.example.msusportsapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.msusportsapp.R;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        TextView title = findViewById(R.id.articleTitle);
        TextView article = findViewById(R.id.articleDescription);



        title.setText(getIntent().getStringExtra("title"));
        article.setText(getIntent().getStringExtra("description"));


    }
}
