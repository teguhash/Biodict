package com.hift.biodict;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TeguhAS on 25-Jul-17.
 */

public class DBBackend extends DBObject {
    private String languageMode;

    public DBBackend(Context context, String mode) {
        super(context);
        this.languageMode = mode;
    }

    public WordsList makeWordsList(String mode) {
        String query = "select * from DictData";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);

        WordsList result = new WordsList(mode);

        ArrayList<String> bahasaWordsList = new ArrayList<String>();
        ArrayList<String> latinWordList = new ArrayList<>();
        ArrayList<String> englishWordList = new ArrayList<>();

        Map<String, Integer> bahasaEntryMap = new HashMap<>();
        Map<String, Integer> latinEntryMap = new HashMap<>();
        Map<String, Integer> englishEntryMap = new HashMap<>();

        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                String indo = cursor.getString(cursor.getColumnIndexOrThrow("NamaIndonesia"));
                String latin = cursor.getString(cursor.getColumnIndexOrThrow("NamaIlmiah"));
                String eng = cursor.getString(cursor.getColumnIndexOrThrow("NamaInggris"));

                bahasaWordsList.add(indo);
                latinWordList.add(latin);
                englishWordList.add(eng);

                index++;
                bahasaEntryMap.put(indo.toUpperCase(), index);
                latinEntryMap.put(latin.toUpperCase(), index);
                englishEntryMap.put(eng.toUpperCase(), index);

            } while (cursor.moveToNext());
        }
        cursor.close();
        result.setBahasaWordsList(bahasaWordsList);
        result.setLatinWordsList(latinWordList);
        result.setEnglishWordsList(englishWordList);

        result.setBahasaEntryMap(bahasaEntryMap);
        result.setLatinEntryMap(latinEntryMap);
        result.setEnglishEntryMap(englishEntryMap);

        return result;
    }

    public WordsList makeWordsList() {
        String query = "select * from DictData";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);

        WordsList result = new WordsList(this.languageMode);

        ArrayList<String> bahasaWordsList = new ArrayList<String>();
        ArrayList<String> latinWordList = new ArrayList<>();
        ArrayList<String> englishWordList = new ArrayList<>();

        Map<String, Integer> bahasaEntryMap = new HashMap<>();
        Map<String, Integer> latinEntryMap = new HashMap<>();
        Map<String, Integer> englishEntryMap = new HashMap<>();

        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                String indo = cursor.getString(cursor.getColumnIndexOrThrow("NamaIndonesia")).trim().replaceAll("[^a-zA-Z\\s]", "");
                String latin = cursor.getString(cursor.getColumnIndexOrThrow("NamaIlmiah")).trim().replaceAll("[^a-zA-Z\\s]", "");
                String eng = cursor.getString(cursor.getColumnIndexOrThrow("NamaInggris")).trim().replaceAll("[^a-zA-Z\\s]", "");

                bahasaWordsList.add(indo);
                latinWordList.add(latin);
                englishWordList.add(eng);

                index++;
                bahasaEntryMap.put(indo.toUpperCase(), index);
                latinEntryMap.put(latin.toUpperCase(), index);
                englishEntryMap.put(eng.toUpperCase(), index);

            } while (cursor.moveToNext());
        }
        cursor.close();
        result.setBahasaWordsList(bahasaWordsList);
        result.setLatinWordsList(latinWordList);
        result.setEnglishWordsList(englishWordList);

        result.setBahasaEntryMap(bahasaEntryMap);
        result.setLatinEntryMap(latinEntryMap);
        result.setEnglishEntryMap(englishEntryMap);

        return result;
    }

    public DictObject getDictDataById(int dictId) {

        DictObject dictObject = null;

        String query = "select * from DictData where _id = " + dictId;

        Cursor cursor = this.getDbConnection().rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                dictObject = new DictObject();

                dictObject.setIndonesiaName(cursor.getString(cursor.getColumnIndexOrThrow("NamaIndonesia")));
                dictObject.setEnglishName(cursor.getString(cursor.getColumnIndexOrThrow("NamaInggris")));
                dictObject.setLatinName(cursor.getString(cursor.getColumnIndexOrThrow("NamaIlmiah")));

                dictObject.setKingdom(cursor.getString(cursor.getColumnIndexOrThrow("Kingdom")));
                dictObject.setSubKingdom(cursor.getString(cursor.getColumnIndexOrThrow("SubKingdom")));
                dictObject.setSuperDivisio(cursor.getString(cursor.getColumnIndexOrThrow("SuperDivisio")));
                dictObject.setDivisio(cursor.getString(cursor.getColumnIndexOrThrow("Divisio")));
                dictObject.setKelas(cursor.getString(cursor.getColumnIndexOrThrow("Kelas")));
                dictObject.setSubKelas(cursor.getString(cursor.getColumnIndexOrThrow("SubKelas")));
                dictObject.setOrdo(cursor.getString(cursor.getColumnIndexOrThrow("Ordo")));
                dictObject.setFamili(cursor.getString(cursor.getColumnIndexOrThrow("Famili")));
                dictObject.setGenus(cursor.getString(cursor.getColumnIndexOrThrow("Genus")));
                dictObject.setSpesies(cursor.getString(cursor.getColumnIndexOrThrow("Spesies")));

                dictObject.setMorphology(cursor.getString(cursor.getColumnIndexOrThrow("Morfologi")));
                dictObject.setManfaat(cursor.getString(cursor.getColumnIndexOrThrow("Manfaat")));

                dictObject.setImageBlob(cursor.getBlob(cursor.getColumnIndexOrThrow("Gambar")));
            } while (cursor.moveToNext());

        }

        cursor.close();

        return dictObject;
    }

    public static Bitmap decodeBlob(byte[] blob) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
        return BitmapFactory.decodeStream(inputStream);
    }
}
