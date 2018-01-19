package com.example.artemqa.bestlcd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.artemqa.bestlcd.model.Monitor;

import java.util.ArrayList;

/**
 * Created by artemqa on 14.01.2018.
 */

public class Helper {
public static final String NAME_PREFERENCES = "PREF_MONITOR";
    public static final String PREF_COST_INDEX = "COST_INDEX";
    public static final String PREF_RESOLUTION_INDEX = "RESOLUTION_INDEX";
    public static final String PREF_DIAGONAL_INDEX ="DIAGONAL_INDEX";
    public static final String PREF_MAX_FRR_INDEX = "MAX_FRR_INDEX";
    public static final String PREF_TYPE_MATRIX_INDEX = "TYPE_MATRIX_INDEX";
    public static final String PREF_SCORE_MARKET_INDEX = "SCORE_MARKET_INDEX";
    public static final String INTENT_RV_URL = "INTENT_RECYCLE_VIEW_URL";
    public static final String REALM_URL = "http://94.250.253.154:9080/~/bestlcd";
    public static final String REALM_AUTH = "http://94.250.253.154:9080/auth";
    public static final String REALM_LOGIN = "bestlcduser";
    public static final String REALM_PASS = "12345678";


    public static ArrayList<Monitor> addItems(){
        ArrayList<Monitor> monitorsList = new ArrayList<>();
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13793418","https://avatars.mds.yandex.net/get-mpic/200316/img_id8125285104942622969/9hq",8130,"1920x1080",23.5,72,"TN",4.0,"Samsung C24F390FHI",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/10709874","https://avatars.mds.yandex.net/get-mpic/96484/img_id6699230478039223263/9hq",6699,"1920x1080",24,60,"TN",4.0,"Samsung S24D300H",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13579255","https://avatars.mds.yandex.net/get-mpic/199079/img_id5755038409424601189/9hq",9437,"2560x1080",25,75,"IPS",4.5,"LG 25UM58",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/12660464","https://avatars.mds.yandex.net/get-mpic/96484/img_id1279787909686372498/9hq",15488,"3840x2160",28,60,"TN",4.0,"Samsung U28E590D",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/14141338","https://avatars.mds.yandex.net/get-mpic/96484/img_id6277443062408493497/9hq",14190,"1920x1080",24,144,"TN",4.0,"BenQ ZOWIE XL2411",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13793416","https://avatars.mds.yandex.net/get-mpic/195452/img_id6371881203942818822/9hq",11298,"1920x1080",27,60,"TN",4.0,"Samsung C27F390FHI",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/7349236","https://avatars.mds.yandex.net/get-mpic/96484/img_id9106994921983194226/9hq",14674,"1920x1200",24,61,"IPS",4.5,"DELL U2412M",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/10461526","https://avatars.mds.yandex.net/get-mpic/195452/img_id2563252984693774644/9hq",4890,"1920x1080",21.5,76,"TN",4.0,"Philips 223V5LSB",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13445127","https://avatars.mds.yandex.net/get-mpic/199079/img_id3758590608469147531/9hq",9430,"1920x1080",23.8,76,"IPS",4.5,"AOC I2481FXH",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/10767802","https://avatars.mds.yandex.net/get-mpic/200316/img_id7650445623990363147/9hq",4190,"1366x768",18.5,60,"TN",4.0,"Acer K192HQLb",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13793434","https://avatars.mds.yandex.net/get-mpic/195452/img_id4758796771694838858/9hq",14685,"1920x1080",27,60,"TN",4.0,"Samsung C27F591FDI",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13041451","https://avatars.mds.yandex.net/get-mpic/96484/img_id459988458595213247/9hq",5993,"1920x1080",21.5,60,"TN",4.5,"BenQ GW2270H",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/1731009916","https://avatars.mds.yandex.net/get-mpic/331398/img_id2536084661909730884.jpeg/9hq",16545,"1920x1080",23.5,144,"TN",4.5,"Samsung C24FG73FQI",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13544747","https://avatars.mds.yandex.net/get-mpic/175985/img_id4906485991163621344/9hq",10025,"1920x1080",23.8,76,"IPS",4.5,"AOC I2475PXQU",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13828256","https://avatars.mds.yandex.net/get-mpic/96484/img_id7034850202076662595/9hq",7685,"1920x1080",23,76,"IPS",4.5,"AOC I2381FH",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13927009","https://avatars.mds.yandex.net/get-mpic/175985/img_id8183278700758048190/9hq",15765,"1920x1080",31.5,60,"TN",4.5,"Samsung C32F391FWI",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13793425","https://avatars.mds.yandex.net/get-mpic/200316/img_id7438178198420713869/9hq",7478,"1920x1080",23.5,60,"TN",4.0,"Samsung S24F350FHI",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/14138155","https://avatars.mds.yandex.net/get-mpic/200316/img_id8983794419407165301/9hq",19327,"1920x1080",24,144,"TN",4.5,"BenQ ZOWIE XL2430",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/12744137","https://avatars.mds.yandex.net/get-mpic/195452/img_id4760562543031237791/9hq",7932,"1920x1080",23.8,60,"TN",4.5,"BenQ GW2470H",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/10414160","https://avatars.mds.yandex.net/get-mpic/199079/img_id4692318448969695322/9hq",11990,"1920x1080",27,75,"IPS",4.0,"AOC i2769Vm",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/13857340","https://avatars.mds.yandex.net/get-mpic/96484/img_id7034850202076662595/9hq",7210,"1920x1080",21.5,76,"IPS",4.5,"AOC I2281FWH",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/14207061","https://avatars.mds.yandex.net/get-mpic/195452/img_id4889835356358300605/9hq",12560,"1920x1080",27,72,"TN",4.5,"Samsung C27F396FHI",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/12410499","https://avatars.mds.yandex.net/get-mpic/96484/img_id2272741139304836131/9hq",8055,"1920x1080",23.6,60,"TN",4.0,"Samsung S24E390HL",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/10634270","https://avatars.mds.yandex.net/get-mpic/195452/img_id551408371333636186/9hq",14479,"1920x1080",23.8,60,"IPS",4.5,"DELL U2414H",0));
        monitorsList.add(new Monitor("https://market.yandex.ru/product/1713282452","https://avatars.mds.yandex.net/get-mpic/195452/img_id8380637086387350787/9hq",37290,"2560x1080",34,144,"IPS",4.0,"LG 34UC79G",0));
        return monitorsList;
    }
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
