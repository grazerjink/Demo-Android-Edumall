package vn.kjapps.freakingmath;

import java.util.Random;

/**
 * Created by KJ Mok on 07/02/2017.
 */

public class GenerateLevel {
    //Diem cua moi level
    public static final int EASY = 15;
    public static final int NORMAL = 30;
    public static final int HARD = 50;

    public static LevelModel generateLevel(int count) {
        LevelModel lvl = new LevelModel();
        Random rd = new Random();

        //Get current difficult level
        if(count <= EASY) lvl.difficultLevel = 1;
        else
            if (count <= NORMAL) lvl.difficultLevel = 2;
            else
                if (count <= HARD) lvl.difficultLevel = 3;
                else
                    lvl.difficultLevel = 4;

        //random operator
        lvl.operator =  rd.nextInt(lvl.difficultLevel);

        //random operation
        lvl.x = rd.nextInt(lvl.arrMaxOperatorValue[lvl.difficultLevel])+1;
        lvl.y = rd.nextInt(lvl.arrMaxOperatorValue[lvl.difficultLevel])+1;

        lvl.correctWrong = rd.nextBoolean();

        //random result
        if(lvl.correctWrong == false) {
            switch (lvl.operator) {
                case LevelModel.ADD :
                    do {
                        lvl.result = rd.nextInt(lvl.arrMaxOperatorValue[lvl.difficultLevel]);
                    } while (lvl.result == (lvl.x + lvl.y));
                    break;
                case LevelModel.MUL :
                    do {
                        lvl.result = rd.nextInt(lvl.arrMaxOperatorValue[lvl.difficultLevel]);
                    } while (lvl.result == (lvl.x * lvl.y));
                    break;
                case LevelModel.DIV :
                    do {
                        lvl.result = rd.nextInt(lvl.arrMaxOperatorValue[lvl.difficultLevel]);
                    } while (lvl.result == (lvl.x /lvl.y));
                    break;
                case LevelModel.SUB :
                    do {
                        lvl.result = rd.nextInt(lvl.arrMaxOperatorValue[lvl.difficultLevel]);
                    } while (lvl.result == (lvl.x - lvl.y));
                    break;
                default:
                    break;
            }
        } else {
            switch (lvl.operator) {
                case LevelModel.ADD :
                    lvl.result = (lvl.x + lvl.y);
                    break;
                case LevelModel.MUL :
                    lvl.result = (lvl.x * lvl.y);
                    break;
                case LevelModel.DIV :
                    lvl.result = (lvl.x / lvl.y);
                    break;
                case LevelModel.SUB :
                    lvl.result = (lvl.x - lvl.y);
                    break;
                default:
                    break;
            }
        }
        lvl.strOperator = String.valueOf(lvl.x) + lvl.arrOperatorText[lvl.operator]
                                                + String.valueOf(lvl.y);
        lvl.strResult = LevelModel.EQUAL_TEXT + String.valueOf(lvl.result);
        return lvl;
    }
}
