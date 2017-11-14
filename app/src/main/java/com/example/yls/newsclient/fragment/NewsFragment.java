package com.example.yls.newsclient.fragment;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.yls.newsclient.NewsEntity;
import com.example.yls.newsclient.R;
import com.example.yls.newsclient.URLManager;
import com.example.yls.newsclient.activity.NewsDetailActivity;
import com.example.yls.newsclient.adapter.NewsAdapter;
import com.example.yls.newsclient.base.BaseFragment;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by yls on 2017/6/27.
 */

public class NewsFragment extends BaseFragment {

    private static final String TAG = "NewsFragment";
    private TextView mTextView;
    private String channelId;
    private ListView mListView;
    private SpringView mSpringView;
    private Handler mHandler = new Handler();
    private NewsAdapter mNewsAdapter;
    private List<NewsEntity.ResultBean> listData;
    private View mHeadView;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViews() {

//        mTextView = (TextView) mRootView.findViewById(R.id.id_fragment_new_tv);
//        mTextView.setText(channelId);
        mListView = (ListView) mRootView.findViewById(R.id.id_news_list_view);
        mSpringView = (SpringView) mRootView.findViewById(R.id.spring_veiw);

        mNewsAdapter = new NewsAdapter(getContext(),null);
        mListView.setAdapter(mNewsAdapter);

        mSpringView.setHeader(new DefaultHeader(getContext()));
        mSpringView.setFooter(new DefaultFooter(getContext()));

        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer(true);

            }

            @Override
            public void onLoadmore() {
                getDataFromServer(false);

            }
        });
    }


    @Override
    protected void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = position;
                if (mListView.getHeaderViewsCount() > 0){
                    index = index -1;
                }

                NewsEntity.ResultBean newBean = (NewsEntity.ResultBean) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("news", newBean);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initDate() {
        getDataFromServer(true);
    }
    private int pageNo = 1;

    /**
     *
     * @param refresh true下拉刷新，false表示加载下一页数据
     */
    private void getDataFromServer(final boolean refresh) {

        if (refresh)
            pageNo = 1;
            String url = URLManager.getUrl(channelId, pageNo);

            HttpUtils utils = new HttpUtils();
            utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String json = responseInfo.result;
                    showToast("获取数据成功");
                    //替换json中的新闻类别
                    json = json.replace(channelId, "result");
                    Gson gson = new Gson();
                    NewsEntity newsEntity = gson.fromJson(json, NewsEntity.class);
                    listData = newsEntity.getResult();

                    if (refresh){
                        showDatas(listData);
                    }else {
                        mNewsAdapter.appendDatas(listData);
                    }
                    pageNo++;
                    Log.d(TAG, "onSuccess: pageNo-----------------"+pageNo);
                    mSpringView.onFinishFreshAndLoad();
                }


                @Override
                public void onFailure(HttpException error, String msg) {
                    error.printStackTrace();
                }
            });

    }





    private void showDatas(List<NewsEntity.ResultBean> listData) {
        if (mListView.getHeaderViewsCount() > 0){
            mListView.removeHeaderView(mHeadView);
        }
        NewsEntity.ResultBean firstNews = listData.get(0);

        if (firstNews == null || firstNews.getAds() == null || firstNews.getAds().size() == 0) {
            showToast("未获取到数据");
            return;
        }

//        显示轮播图
        List<NewsEntity.ResultBean.AdsBean> ads = firstNews.getAds();

        if (ads != null && ads.size() > 0) {
            mHeadView = View.inflate(getContext(), R.layout.list_header, null);
            //如果有头部删除第一条新闻
            listData.remove(0);
            SliderLayout sliderLayout = (SliderLayout) mHeadView.findViewById(R.id.slider_layout);
            for (int i = 0; i < ads.size(); i++) {
                final NewsEntity.ResultBean.AdsBean adsBean = ads.get(i);
                TextSliderView textSliderView = new TextSliderView(getContext());
                textSliderView.description(adsBean.getTitle()).image(adsBean.getImgsrc());
                //添加子界面
                sliderLayout.addSlider(textSliderView);
                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showToast(slider.getDescription());
                    }
                });
            }
            mListView.addHeaderView(mHeadView);
        }else {

        }
//        显示新闻列表
       mNewsAdapter.setDatas(listData);


    }
}
