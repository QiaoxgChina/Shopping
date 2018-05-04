package com.international.baselib;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppConfig {

    private static final String TAG = "AppConfig";

    //debug模式是否开启
    public static final boolean DEBUG = true;

    //主域名
    public static final String BASE_URL = "https://api.github.com";

    private static final String[] IMAGE_URL_DATA = {"http://image1.92bizhi.com/art_green-widescreen_01-2560x1600.jpg",
            "http://imgstore.cdn.sogou.com/app/a/100540002/782261.jpg",
            "http://pic1.win4000.com/wallpaper/0/51832372d67d8.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2912000328,3978772011&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2152652095,3426448541&fm=27&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=3422429041,2382682538&fm=27&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=488495735,2572691798&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2697023187,1528768895&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1942689720,4186059666&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1661241254,875695806&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=1594742820,3977500836&fm=27&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=2568504687,22561432&fm=27&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2388027374,1014107545&fm=27&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=396429930,2371619419&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=355534297,458588263&fm=27&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3690835503,748741159&fm=27&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2744264078,315776389&fm=27&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=993451558,1014258290&fm=27&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1793768479,3460204609&fm=27&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3097456750,2068343060&fm=27&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1347812421,1935691123&fm=27&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=574475081,3422538218&fm=27&gp=0.jpg"};

    /**
     * 随机获取图片url
     *
     * @param count 获取的数量
     * @return
     */
    public static List<String> getRandomImageUrl(int count) {
        if (count <= 0) {
            count = 1;
        }

        if (count > IMAGE_URL_DATA.length) {
            count = IMAGE_URL_DATA.length;
        }

        List<String> urls = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int index = rand.nextInt(IMAGE_URL_DATA.length);

            Log.e(TAG, "getRandomImageUrl: total size is " + IMAGE_URL_DATA.length + " =========== index is " + index);

            if (!urls.contains(IMAGE_URL_DATA[index])) {
                urls.add(IMAGE_URL_DATA[index]);
            }
        }

        return urls;
    }

}
