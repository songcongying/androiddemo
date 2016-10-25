package com.example.myapplication.model;

/**
 * Created by Song Congying on 2016/10/24.
 */
public class WordModel {

    /** 表名 */
    public static final String TABLE_NAME = "word_table";
    /** 英文单词名称 */
    public static final String ENGLISH_WORD = "word";
    /** 英文单词翻译 */
    public static final String TRANSLATION = "translation";
    /** 是否正确 */
    public static final String ISTRUE = "istrue";
    /** 是否读过,默认是0 */
    public static final String ISHAVAREAD = "isread";
    /** 读的次数，默认是0 */
    public static final String READTIME = "readtime";

    private String Word;
    private String Traslation;
    private int IsTrue;
    private int isHavaRead;
    private int read_time;

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getTraslation() {
        return Traslation;
    }

    public void setTraslation(String traslation) {
        Traslation = traslation;
    }

    public int getIsTrue() {
        return IsTrue;
    }

    public void setIsTrue(int isTrue) {
        IsTrue = isTrue;
    }

    public int getIsHavaRead() {
        return isHavaRead;
    }

    public void setIsHavaRead(int isHavaRead) {
        this.isHavaRead = isHavaRead;
    }

    public int getRead_time() {
        return read_time;
    }

    public void setRead_time(int read_time) {
        this.read_time = read_time;
    }

    public WordModel(String word, String traslation, int isTrue, int isHavaRead, int read_time) {
        Word = word;
        Traslation = traslation;
        IsTrue = isTrue;
        this.isHavaRead = isHavaRead;
        this.read_time = read_time;
    }
}
