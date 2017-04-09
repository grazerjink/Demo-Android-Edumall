package vn.kjapps.freakingmath;

/**
 * Created by KJ Mok on 07/02/2017.
 */

public class LevelModel {
    public int difficultLevel = 1;

    //Ham` + - x :
    public static final int ADD = 0;
    public static final int SUB = 1;
    public static final int MUL = 2;
    public static final int DIV = 3;

    //+ - x : the hien tren text view
    public static final String ADD_TEXT = "+";
    public static final String SUB_TEXT = "-";
    public static final String MUL_TEXT = "x";
    public static final String DIV_TEXT = ":";
    public static final String EQUAL_TEXT = "=";
    public static final String[] arrOperatorText = {ADD_TEXT,SUB_TEXT,MUL_TEXT,DIV_TEXT};

    public int x;
    public int y;
    public int result;
    public int operator;
    public boolean correctWrong;
    public String strOperator = "";
    public String strResult = "";

    public static final int MAX_OPERATOR_LEVEL_EASY = 10;
    public static final int MAX_OPERATOR_LEVEL_NORMAL = 20;
    public static final int MAX_OPERATOR_LEVEL_HARD = 50;

    public static final int[] arrMaxOperatorValue = {MAX_OPERATOR_LEVEL_EASY,MAX_OPERATOR_LEVEL_NORMAL, MAX_OPERATOR_LEVEL_HARD};


}
