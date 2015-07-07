package com.yey.read.home.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.home.entity.Book;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class BookDetailActivity extends BaseActivity {

    @ViewInject(R.id.book_cover) ImageView bookCoverImageView;
    @ViewInject(R.id.book_author) TextView bookAuthorTextView;
    @ViewInject(R.id.book_isbn) TextView bookIsbnTextView;
    @ViewInject(R.id.book_publisher) TextView bookPublisherTextView;
    @ViewInject(R.id.book_title) TextView bookTitleTextView;
    @ViewInject(R.id.book_translate) TextView bookTranslateTextView;
    @ViewInject(R.id.reason_content) TextView reasonContentTextView;
    @ViewInject(R.id.age_content) TextView ageContentTextView;
    @ViewInject(R.id.read_guide) Button readGuideButton;
    @ViewInject(R.id.author_intro_content) TextView authorIntroContentTextView;
    @ViewInject(R.id.detail_intro_content) TextView detailIntroContentTextView;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ViewUtils.inject(this);

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open("bookList.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            Gson gson = new Gson();
            Object obj = gson.fromJson(jsonObject.getString("book"), Book.class);
            book = (Book)obj;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Bundle bundle = this.getIntent().getExtras();
//        book = (Book)bundle.getSerializable("detailBook");
        setBookInfo(book);

    }

    private void setBookInfo(Book book) {
        imageLoader.displayImage(book.getCover(), bookCoverImageView);
        bookAuthorTextView.setText("作者: " + book.getAuthor());
        bookIsbnTextView.setText("ISBN: "+book.getIsbn());
        bookTitleTextView.setText(book.getTitle());
        bookPublisherTextView.setText("出版社: "+book.getPublisher());
        bookTranslateTextView.setText("翻译: "+book.getTranslate());
        reasonContentTextView.setText(book.getReason());
        ageContentTextView.setText(book.getAge());
        authorIntroContentTextView.setText(book.getAuthorIntro());
        detailIntroContentTextView.setText(book.getDetailIntro());
    }
}
