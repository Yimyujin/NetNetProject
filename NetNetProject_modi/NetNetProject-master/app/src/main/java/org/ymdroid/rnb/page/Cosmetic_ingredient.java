package org.ymdroid.rnb.page;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import org.ymdroid.rnb.R;
import org.ymdroid.rnb.event.ListViewAnalysisAdapter;
import org.ymdroid.rnb.key.CosInfo;
import org.ymdroid.rnb.key.Review;

/**
 * Created by yj on 16. 5. 22..
 */
public class Cosmetic_ingredient extends ActionBarActivity {
    private ListView mListView = null;
    private ListViewAnalysisAdapter mAdapter = null;
    private CosInfo A  = new CosInfo(47, 0);
    private Resources res ;

    private int[] dangerImg = {
            R.drawable.ing_null,R.drawable.ing_one, R.drawable.ing_two, R.drawable.ing_three, R.drawable.ing_four,
            R.drawable.ing_five, R.drawable.ing_six, R.drawable.ing_seven, R.drawable.ing_eight, R.drawable.ing_nine
    };
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic_ingredient);
        getSupportActionBar().setTitle("화장품 성분 정보");
        mListView = (ListView) findViewById(R.id.ls_cos_ingredient);
        mAdapter = new ListViewAnalysisAdapter(this);
         res = getResources();
         setTable();
         insertData();
    }

    public void setTable(){
        A.krIng[0] = "정제수";
        A.krIng[1] = "에탄올";
        A.krIng[2] = "부틸렌글라이콜";
        A.krIng[3] = "글리세린";
        A.krIng[4] = "알부틴";
        A.krIng[5] = "사이클로펜타실록산";
        A.krIng[6] = "디메치콘";
        A.krIng[7] = "디메치콘/비닐디메치콘크로스폴리머";
        A.krIng[8] = "비피다발효용해물";
        A.krIng[9] = "카보머";
        A.krIng[10] = "폴리아크릴아마이드";
        A.krIng[11] = "피이지-40하이드로제네이티드캐스터오일";
        A.krIng[12] = "폴리소르베이트60";
        A.krIng[13] = "글리세릴아크릴레이트/아크릴릭애씨드코폴리머";
        A.krIng[14] = "프로필렌글라이콜";
        A.krIng[15] = "C13-1이소파라핀";
        A.krIng[16] = "1,2-헥산디올";
        A.krIng[17] = "소듐하이알루로네이트";
        A.krIng[18] = "녹차추출물";
        A.krIng[19] = "판테놀";
        A.krIng[20] = "카프릴릴글라이콜";
        A.krIng[21] = "글리세릴카프릴레이트";
        A.krIng[22] = "카프릭하이드록사믹애씨드";
        A.krIng[23] = "글리세릴라우레이트";
        A.krIng[24] = "오레가노잎추출물";
        A.krIng[25] = "유칼립투스잎오일";
        A.krIng[26] = "들깨오일";
        A.krIng[27] = "옥탄디올";
        A.krIng[28] = "트로폴론";
        A.krIng[29] = "쌀겨추출물";
        A.krIng[30] = "글레디치아아우스트랄리스열매추출물";
        A.krIng[31] = "라케모사승마뿌리추출물";
        A.krIng[32] = "백지추출물";
        A.krIng[33] = "백급추출물";
        A.krIng[34] = "녹두추출물";
        A.krIng[35] = "콜로신스열매추출물";
        A.krIng[36] = "감송향추출물";
        A.krIng[37] = "사인추출물";
        A.krIng[38] = "클로브꽃추출물";
        A.krIng[39] = "자작나무수액";
        A.krIng[40] = "감초추출물";
        A.krIng[41] = "포타슘하이드록사이드";
        A.krIng[42] = "잔탄검";
        A.krIng[43] = "라우레스-7";
        A.krIng[44] = "디소듐이디티에이";
        A.krIng[45] = "아스코빌팔미테이트";
        A.krIng[46] = "향료";


        A.enIng[0] = "Water Aqua";
        A.enIng[1] = "Alcohol";
        A.enIng[2] = "Butylene Glycol";
        A.enIng[3] = "Glycerin";
        A.enIng[4] = "Arbutin";
        A.enIng[5] = "Cyclopentasiloxane";
        A.enIng[6] = "Dimethicone";
        A.enIng[7] = "Dimethicone/Vinyl Dimethicone Crosspolymer";
        A.enIng[8] = "Bifida Ferment Lysate";
        A.enIng[9] = "Carbomer";
        A.enIng[10] = "Polyacrylamide";
        A.enIng[11] = "PEG-40 Hydrogenated Castor Oil";
        A.enIng[12] = "Polysorbate 60";
        A.enIng[13] = "Glyceryl Acrylate/Acrylic Acid Copolymer";
        A.enIng[14] = "Propylene Glycol";
        A.enIng[15] = "C13-1Isoparaffin";
        A.enIng[16] = "1,2-Hexanediol";
        A.enIng[17] = "Sodium Hyaluronate";
        A.enIng[18] = "Camellia Sinensis Leaf Extract";
        A.enIng[19] = "Panthenol";
        A.enIng[20] = "Caprylyl Glycol";
        A.enIng[21] = "Glyceryl Caprylat";
        A.enIng[22] = "Caprylhydroxamic Acid";
        A.enIng[23] = "Glyceryl Laurate";
        A.enIng[24] = "Origanum Vulgare Leaf Extract";
        A.enIng[25] = "Eucalyptus Globulus Leaf Oil";
        A.enIng[26] = "Perilla Ocymoides Seed Oil";
        A.enIng[27] = "Octanediol";
        A.enIng[28] = "Tropolone";
        A.enIng[29] = "Oryza Sativa (Rice) Bran Extract";
        A.enIng[30] = "Gleditsia Australis Fruit Extract";
        A.enIng[31] = "Cimicifuga Racemosa Root Extract";
        A.enIng[32] = "Angelica Dahurica Root Extract";
        A.enIng[33] = "Bletilla Striata Root Extract";
        A.enIng[34] = "Phaseolus Radiatus Seed Extract";
        A.enIng[35] = "Citrullus Colocynthis Fruit Extract";
        A.enIng[36] = "Nardostachys Chinensis Root Extract";
        A.enIng[37] = "Amomum Xanthioides Seed Extract";
        A.enIng[38] = "Eugenia Caryophyllus (Clove) Flower Extract";
        A.enIng[39] = "Betula Alba Juice";
        A.enIng[40] = "Glycyrrhiza Glabra (Licorice) Root Extract";
        A.enIng[41] = "Potassium Hydroxide";
        A.enIng[42] = "Xanthan Gum";
        A.enIng[43] = "Laureth-7";
        A.enIng[44] = "Disodium EDTA";
        A.enIng[45] = "Ascorbyl Palmitate";
        A.enIng[46] = "Flavor";

        A.danger[0] = 1;
        A.danger[1] = 2;
        A.danger[2] = 1;
        A.danger[3] = 2;
        A.danger[4] = 1;
        A.danger[5] = 3;
        A.danger[6] = 3;
        A.danger[7] = 1;
        A.danger[8] = 1;
        A.danger[9] = 0;
        A.danger[10] = 4;
        A.danger[11] = 3;
        A.danger[12] = 3;
        A.danger[13] = 1;
        A.danger[14] = 3;
        A.danger[15] = 1;
        A.danger[16] = 1;
        A.danger[17] = 1;
        A.danger[18] = 1;
        A.danger[19] = 1;
        A.danger[20] = 1;
        A.danger[21] = 1;
        A.danger[22] = 1;
        A.danger[23] = 3;
        A.danger[24] = 1;
        A.danger[25] = 2;
        A.danger[26] = 0;
        A.danger[27] = 1;
        A.danger[28] = 1;
        A.danger[29] = 1;
        A.danger[30] = 0;
        A.danger[31] = 1;
        A.danger[32] = 1;
        A.danger[33] = 1;
        A.danger[34] = 0;
        A.danger[35] = 0;
        A.danger[36] = 0;
        A.danger[37] = 0;
        A.danger[38] = 5;
        A.danger[39] = 1;
        A.danger[40] = 1;
        A.danger[41] = 3;
        A.danger[42] = 1;
        A.danger[43] = 3;
        A.danger[44] = 1;
        A.danger[45] = 1;
        A.danger[46] = 8;
    }

    public void insertData()
    {
        int size = A.enIng.length;
        for(int i=0; i<size; i++) {
            mAdapter.addItem(BitmapFactory.decodeResource(res, dangerImg[A.danger[i]]),A.krIng[i], A.enIng[i]);
        }
        mListView.setAdapter(mAdapter);
    }

}

