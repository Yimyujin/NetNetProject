package org.ymdroid.rnb.event;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by kimminyoung on 2016-05-05.
 */
public class ListData {
    // 아이콘
    public Bitmap mIcon;

    // 제목
    public String mTitle;

    // 날짜
    public String mDate;

    //별점
    public Bitmap mScore;

    //사용자이름
    public String mName;

    //리뷰 내용
    public String mText;

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(ListData mListDate_1, ListData mListDate_2) {
            return sCollator.compare(mListDate_1.mTitle, mListDate_2.mTitle);
        }
    };
}
