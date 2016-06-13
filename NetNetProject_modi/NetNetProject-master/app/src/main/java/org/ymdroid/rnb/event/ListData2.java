package org.ymdroid.rnb.event;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by yj on 16. 5. 22..
 */
public class ListData2 {

    // 제목
    public String mTitle;

    // 날짜
    public String mDate;

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
