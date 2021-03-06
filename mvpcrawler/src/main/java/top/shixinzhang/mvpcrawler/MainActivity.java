/*
 * Copyright (c) 2017. shixinzhang (shixinzhang2016@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.shixinzhang.mvpcrawler;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.shixinzhang.mvpcrawler.mvp.CrawlerContract;
import top.shixinzhang.mvpcrawler.mvp.model.CrawlerModel;
import top.shixinzhang.mvpcrawler.mvp.view.SellNiceCarView;
import top.shixinzhang.utils.ServiceUtils;
import top.shixinzhang.utils.ShellUtils;

import static top.shixinzhang.mvpcrawler.DataCrawlerService.MODE_KEY;
import static top.shixinzhang.mvpcrawler.mvp.CrawlerContract.Model.MODE_START;

/**
 * Description:
 * <br> 使用 MVP 架构开发爬虫，便于复用代码
 * <p>
 * <br> Created by shixinzhang on 17/8/7.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <br> https://about.me/shixinzhang
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_sell_nice_car)
    Button mBtnSellNiceCar;
    @BindView(R.id.btn_yi_jie_hao_che)
    Button mBtnYiJieHaoChe;
    @BindView(R.id.btn_stop)
    Button mBtnStop;
    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;

    private CrawlerContract.Model mCrawlerModel;
    private CrawlerContract.View mCrawlerAppView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ShellUtils.testRoot(this);
    }


    @OnClick(R.id.btn_sell_nice_car)
    public void crawlerSellNiceCar() {
        if (!hasEnv()) {
            goToOpenAccessibility(0);
        } else {
            DataCrawlerService.setPresenter(SellNiceCarView.create(), CrawlerModel.create());

            Intent intent = new Intent(this, DataCrawlerService.class);
            intent.putExtra(MODE_KEY, MODE_START);
            startService(intent);
        }
    }

    @OnClick(R.id.btn_stop)
    public void stop() {
        stopService(new Intent(MainActivity.this, DataCrawlerService.class));
    }

    private boolean hasEnv() {
        return ServiceUtils.isAccessibilitySettingsOn(MainActivity.this, "top.shixinzhang.mvpcrawler/top.shixinzhang.mvpcrawler.DataCrawlerService");
    }

    private void goToOpenAccessibility(int requestCode) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, requestCode);  //在当前 Task 中，才能收到回调
    }
}
