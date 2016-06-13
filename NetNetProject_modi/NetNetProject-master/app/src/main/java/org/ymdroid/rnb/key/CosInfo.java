package org.ymdroid.rnb.key;

import java.util.ArrayList;

/**
 * Created by yj on 16. 6. 12..
 */
public class CosInfo {
    public int idx;
    public int img;
    public String name;
    public String brand;
    public String type;
    public int lowPrice;
    public int highPrice;
    public String code; //바코드
    public int[] rc; //추천화장품 인덱스
    public String[] krIng; //한글이름
    public String[] enIng; //영문이름
    public int[] danger; //위험도
    public ArrayList<Review> rv;


    public CosInfo(int ingnum, int rcnum){
        rv = new ArrayList<Review>();
        krIng=  new String[ingnum];
        enIng = new String[ingnum];
        danger = new int[ingnum];
        rc = new int[rcnum];
    }



}
