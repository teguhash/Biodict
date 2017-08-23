package com.hift.biodict;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.text.Normalizer;

public class DictionaryActivity extends AppCompatActivity {
    float screenDensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_activity);

        screenDensity = getResources().getDisplayMetrics().density;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int dictionaryIndex = bundle.getInt("DICTIONARY_INDEX");
        String language = bundle.getString("LANGUAGE");

        DBBackend backend = new DBBackend(this, language);
        DictObject dictEntry = backend.getDictDataById(dictionaryIndex);
        Context mContext = getApplicationContext();

        ScrollView scrollView = (ScrollView) this.findViewById(R.id.root_relative);

        ScrollView.LayoutParams scrollLP = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT);
        int dp8 = getDpFromValue(8);
        scrollLP.setMargins(dp8, dp8, dp8, dp8);

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linearLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        linearLayout.addView(makeCard(mContext, "Indonesia", dictEntry.getIndonesiaName().trim()));
        if (!isNullOrEmpty(dictEntry.getEnglishName())) {
            linearLayout.addView(makeCard(mContext, "Inggris", dictEntry.getEnglishName().trim()));
        }
        if (!isNullOrEmpty(dictEntry.getLatinName())) {
            linearLayout.addView(makeCard(mContext, "Latin", dictEntry.getLatinName().trim()));
        }

        linearLayout.addView(makeCard(mContext, "Gambar", convertBlobToBitmap(dictEntry.getImageBlob())));

        linearLayout.addView(makeCard(mContext, "Klasifikasi", dictEntry));

        if (!isNullOrEmpty(dictEntry.getMorphology())) {
            linearLayout.addView(makeCard(mContext, "Morfologi", dictEntry.getMorphology().trim()));
        }
        if (!isNullOrEmpty(dictEntry.getManfaat())) {
            linearLayout.addView(makeCard(mContext, "Manfaat", dictEntry.getManfaat().trim()));
        }

        linearLayout.setLayoutParams(linearLP);
        scrollView.addView(linearLayout);
        scrollView.setLayoutParams(scrollLP);
        setTitle(camelCase(dictEntry.getIndonesiaName()));
    }

    private CardView makeCard(Context context, String title, String text) {
        CardView card = new CardView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int dp5 = getDpFromValue(3);
        lp.setMargins(dp5, dp5, dp5, dp5);

        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        titleView.setTextColor(Color.WHITE);
        titleView.setTextSize(getDpFromValue(6));
        titleView.setBackgroundColor(Color.parseColor("#42A5F5"));
        titleView.setLayoutParams(lp);
        titleView.setTypeface(Typeface.MONOSPACE);

        TextView textView = new TextView(context);
        textView.setTextSize(getDpFromValue(6));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextColor(Color.DKGRAY);
        textView.setLayoutParams(lp);
        textView.setTypeface(Typeface.SERIF);
        if (title.equalsIgnoreCase("Morfologi") || title.equalsIgnoreCase("Manfaat")) {
            textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(text);
        } else {
            text = camelCase(text);
            textView.setText(text);
        }

        linearLayout.addView(titleView);
        linearLayout.addView(textView);
        linearLayout.setLayoutParams(lp);

        card.addView(linearLayout);

        CardView.LayoutParams cardLP = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
        card.setLayoutParams(cardLP);
        card.setCardElevation(getDpFromValue(3));
        card.setUseCompatPadding(true);
        return card;
    }

    private CardView makeCard(Context context, String title, Bitmap image) {
        CardView card = new CardView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int dp5 = getDpFromValue(3);
        lp.setMargins(dp5, dp5, dp5, dp5);

        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        titleView.setTextColor(Color.WHITE);
        titleView.setTypeface(Typeface.MONOSPACE);
        titleView.setTextSize(getDpFromValue(6));
        titleView.setBackgroundColor(Color.parseColor("#42A5F5"));
        titleView.setLayoutParams(lp);

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
        imageView.setImageBitmap(image);
        imageView.setLayoutParams(lp);

        linearLayout.addView(titleView);
        linearLayout.addView(imageView);
        linearLayout.setLayoutParams(lp);

        card.addView(linearLayout);

        CardView.LayoutParams cardLP = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
        card.setLayoutParams(cardLP);
        card.setCardElevation(getDpFromValue(3));
        card.setUseCompatPadding(true);
        return card;
    }

    private CardView makeCard(Context context, String title, DictObject dictObject) {
        CardView card = new CardView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int dp5 = getDpFromValue(3);
        lp.setMargins(dp5, dp5, dp5, dp5);

        TextView titleView = new TextView(context);
        titleView.setText(title);
        titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        titleView.setTextColor(Color.WHITE);
        titleView.setTypeface(Typeface.MONOSPACE);
        titleView.setTextSize(getDpFromValue(6));
        titleView.setBackgroundColor(Color.parseColor("#42A5F5"));
        titleView.setLayoutParams(lp);

        linearLayout.addView(titleView);
        if (!isNullOrEmpty(dictObject.getKingdom())) {
            linearLayout.addView(makeClassCard(context, "Kingdom", dictObject.getKingdom().trim(), 1));
        }
        if (!isNullOrEmpty(dictObject.getSubKingdom())) {
            linearLayout.addView(makeClassCard(context, "Sub-Kingdom", dictObject.getSubKingdom().trim(), 2));
        }
        if (!isNullOrEmpty(dictObject.getSuperDivisio())) {
            linearLayout.addView(makeClassCard(context, "Super Divisio", dictObject.getSuperDivisio().trim(), 3));
        }
        if (!isNullOrEmpty(dictObject.getDivisio())) {
            linearLayout.addView(makeClassCard(context, "Divisio", dictObject.getDivisio().trim(), 4));
        }
        if (!isNullOrEmpty(dictObject.getKelas())) {
            linearLayout.addView(makeClassCard(context, "Kelas", dictObject.getKelas().trim(), 5));
        }
        if (!isNullOrEmpty(dictObject.getSubKelas())) {
            linearLayout.addView(makeClassCard(context, "Sub-Kelas", dictObject.getSubKelas().trim(), 6));
        }
        if (!isNullOrEmpty(dictObject.getOrdo())) {
            linearLayout.addView(makeClassCard(context, "Ordo", dictObject.getOrdo().trim(), 7));
        }
        if (!isNullOrEmpty(dictObject.getFamili())) {
            linearLayout.addView(makeClassCard(context, "Famili", dictObject.getFamili().trim(), 8));
        }
        if (!isNullOrEmpty(dictObject.getGenus())) {
            linearLayout.addView(makeClassCard(context, "Genus", dictObject.getGenus().trim(), 9));
        }
        if (!isNullOrEmpty(dictObject.getSpesies())) {
            linearLayout.addView(makeClassCard(context, "Spesies", dictObject.getSpesies().trim(), 10));
        }
        linearLayout.setLayoutParams(lp);

        card.addView(linearLayout);

        CardView.LayoutParams cardLP = new CardView.LayoutParams(CardView.LayoutParams.MATCH_PARENT, CardView.LayoutParams.WRAP_CONTENT);
        card.setLayoutParams(cardLP);
//        card.setCardElevation(getDpFromValue(3));
//        card.setUseCompatPadding(true);
        return card;
    }

    private LinearLayout makeClassCard(Context context, String classification, String value, int step) {
        LinearLayoutOutlined linearLayout = new LinearLayoutOutlined(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView title = new TextView(context);
        title.setText(classification);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(getDpFromValue(4.5));
        title.setBackgroundColor(Color.parseColor("#1E88E5"));
        title.setPadding(getDpFromValue(4), getDpFromValue(2), getDpFromValue(4), getDpFromValue(2));

        TextView valueView = new TextView(context);
        if (classification.equalsIgnoreCase("Spesies")) {
            valueView.setTypeface(valueView.getTypeface(), Typeface.ITALIC);
        }
        valueView.setText(camelCase(value));
        valueView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        valueView.setTextColor(Color.DKGRAY);
        valueView.setTextSize(getDpFromValue(4.5));
        valueView.setPadding(getDpFromValue(4), getDpFromValue(2), getDpFromValue(4), getDpFromValue(2));

        LinearLayout.LayoutParams textLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        title.setLayoutParams(textLP);
        valueView.setLayoutParams(textLP);

        linearLayout.addView(title);
        linearLayout.addView(valueView);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(step * getDpFromValue(10), getDpFromValue(2), 0, getDpFromValue(2));
        linearLayout.setLayoutParams(lp);

        return linearLayout;
    }

    private int getDpFromValue(double value) {
        return (int) Math.floor(value * screenDensity);
    }

    private Bitmap convertBlobToBitmap(byte[] blob) {
        ByteArrayInputStream is = new ByteArrayInputStream(blob);
        return BitmapFactory.decodeStream(is);
    }

    private String camelCase(String s) {
        System.out.println("camelCase: " + s);
        String result = "";
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[^\\x00-\\x7F]", "");
        if (isNullOrEmpty(s)) {
            return "";
        }
        String[] splitString = s.split(" ");
        System.out.println("split length: " + splitString.length);
        for (int i = 0; i < splitString.length; i++) {
            System.out.println("split string: " + splitString[i]);
            if (!isNullOrEmpty(splitString[i])) {
                if (splitString[i].length() == 1) {
                    result += splitString[i].toUpperCase() + (i != splitString.length - 1 ? " " : "");
                } else {
                    result += splitString[i].substring(0, 1).toUpperCase() + splitString[i].substring(1).toLowerCase() + (i != splitString.length - 1 ? " " : "");
                }
            }
        }
        return result;
    }

    public boolean isNullOrEmpty(String string) {
        if (string == null) {
            return true;
        } else {
            return string.trim().isEmpty();
        }
    }
}
