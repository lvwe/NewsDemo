package com.example.yls.newsclient.activity;

import android.animation.Animator;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yls.newsclient.R;
import com.example.yls.newsclient.base.BaseActivity;

/**
 * Created by yls on 2017/6/26.
 */

public class GuideActivity extends BaseActivity {

    private ImageView mImageView;
    private Button mButton;
    private boolean mExitActivity;
    private int index;
    private MediaPlayer mMediaPlayer;
    private int[] mImgRes = new int[]{R.drawable.ad_new_version1_img1,
                                R.drawable.ad_new_version1_img2,
                                R.drawable.ad_new_version1_img3,
                                R.drawable.ad_new_version1_img4,
                                R.drawable.ad_new_version1_img5,
                                R.drawable.ad_new_version1_img6,
                                R.drawable.ad_new_version1_img7};
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initViews() {
        mImageView = (ImageView) findViewById(R.id.id_guide_iv_bg);
        mButton = (Button) findViewById(R.id.id_guide_btn_start);

        startImgAnimation();
        playMusic();
    }



    private void startImgAnimation() {
        int imgId = mImgRes[index % mImgRes.length];
        mImageView.setImageResource(imgId);
        index++;

        mImageView.setScaleX(1f);
        mImageView.setScaleY(1f);

        mImageView.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(3000)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (!mExitActivity){
                            startImgAnimation();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).start();
    }


    @Override
    protected void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(MainActivity.class,true);
            }
        });
    }
    private void playMusic() {
        //            AssetFileDescriptor fileDescriptor = getAssets().openFd("new_version.mp3");
//            mMediaPlayer = new MediaPlayer();
//            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
//                    0L,fileDescriptor.getLength());
        mMediaPlayer = MediaPlayer.create(this,R.raw.new_version);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.setVolume(1f,1f);
//            mMediaPlayer.prepare();
        mMediaPlayer.start();

    }

    private void stopMusic() {
        if (mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
//            mMediaPlayer.reset();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitActivity = true;
        stopMusic();
    }

    @Override
    protected void initDate() {
    }
}
