package com.example.application.requestmanager;

import android.content.Context;
import android.widget.ImageView;


import com.example.application.utilsmanager.ClientUtilsManager;
import com.squareup.picasso.Picasso;

/**
 * Created by Generator on 12/4/2015.
 */

public class PicassoImageManager {

    public static void picassoLoadSingleImage(Context cont, String imageUrl, ImageView img) {

        ClientUtilsManager.dialogShow(cont, ClientUtilsManager.MESSAGE_WAIT);
        Picasso.with(cont)
                .load(imageUrl)
                .into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        ClientUtilsManager.dialogDismiss();
                    }

                    @Override
                    public void onError() {
                        ClientUtilsManager.dialogDismiss();
                    }
                });

    }

    public static void picassoLoadSingleImageWithNoDialog(Context cont, String imageUrl, ImageView img) {

        Picasso.with(cont)
                .load(imageUrl)
                .resize(100,100)
                .into(img);

    }

}
