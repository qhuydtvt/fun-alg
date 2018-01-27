package tkbases.assetmanager;

import tkbases.utils.SpriteUtils;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by huynq on 5/13/17.
 */
public class SpriteManager {
    public static BufferedImage loadAssetImage(String url) {
        String fullUrl = String.format("assets/images/%s", url);
        if (imageHashMap.containsKey(fullUrl)) {
            return imageHashMap.get(fullUrl);
        } else {
            BufferedImage image = SpriteUtils.loadImage(fullUrl);
            imageHashMap.put(fullUrl, image);
            return image;
        }
    }

    private static HashMap<String, BufferedImage> imageHashMap;

    static  {
        imageHashMap = new HashMap<>();
    }
}
