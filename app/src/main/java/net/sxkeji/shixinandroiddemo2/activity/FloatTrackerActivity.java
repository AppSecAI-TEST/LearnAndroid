package net.sxkeji.shixinandroiddemo2.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import net.sxkeji.shixinandroiddemo2.BaseActivity;
import net.sxkeji.shixinandroiddemo2.R;
import net.sxkeji.shixinandroiddemo2.service.AssistantService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.shixinzhang.sxframework.utils.AlertUtils;
import top.shixinzhang.sxframework.utils.SettingUtils;

/**
 * Description:
 * <br> 悬浮窗显示 Activity 名称信息
 * 学习自：https://github.com/fashare2015/ActivityTracker
 * <p>
 * <br> Created by shixinzhang on 17/5/9.
 * <p>
 * <br> Email: shixinzhang2016@gmail.com
 * <p>
 * <a  href="https://about.me/shixinzhang">About me</a>
 */

public class FloatTrackerActivity extends BaseActivity {
    @BindView(R.id.btn_open_float_button)
    Button mBtnOpenFloatButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_tracker);
        ButterKnife.bind(this);
        SettingUtils.checkOverlayPermission(this, getPackageName());
    }

    @OnClick(R.id.btn_open_float_button)
    public void openFloatButton() {
        if (!SettingUtils.checkAccessibilityOpen(this, AssistantService.class)) {
            AlertUtils.toastShort(this, "获取Activity信息需开启辅助服务");
            SettingUtils.jumpToSettingAccessibility(FloatTrackerActivity.this);
        }
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

    }

    public void addListeners() {

    }
}
