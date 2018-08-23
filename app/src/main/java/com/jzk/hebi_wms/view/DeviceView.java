package com.jzk.hebi_wms.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringRes;
import android.text.Selection;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.data.station.InjectMoldBean;
import com.jzk.hebi_wms.utils.ToastUtils;
import com.jzk.spinnerlibrary.MaterialSpinner;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的设备选择的控件
 *
 * @author jzk
 * create at: 2018/8/10 9:57
 */
public class DeviceView extends AutoLinearLayout {
    private Context mContext;
    /**
     * 是否可以通过相机扫描条码
     */
    private boolean isScanByCamera;
    /**
     * 是否包含Spinner布局
     */
    private boolean isContainSpinner;
    /**
     * 上方的提示文字
     */
    private String textTip;
    /**
     * 输入框提示
     */
    private String inputTip;
    /**
     * 没有匹配的提示
     */
    private String noMapStrTip;
    /**
     * 控件
     */
    private TextView tvTip;
    private EditText etInput;
    private MaterialSpinner spinnerDevice;
    private ImageView ivDevice;

    /**
     * Spinner的源数据
     */
    private List<InjectMoldBean.EqpmentsBean> mDiveces = new ArrayList<>();
    public DeviceView(Context context) {
        super(context);
        initView(context);
    }

    public DeviceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DeviceView);
        isScanByCamera = ta.getBoolean(R.styleable.DeviceView_isScanByCamera, false);
        isContainSpinner = ta.getBoolean(R.styleable.DeviceView_isContainSpinner, true);
        textTip = ta.getString(R.styleable.DeviceView_textTip);
        inputTip = ta.getString(R.styleable.DeviceView_textInput);
        noMapStrTip = ta.getString(R.styleable.DeviceView_noMapStrTip);
        ta.recycle();
        initView(context);
    }

    public DeviceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.device_layout, null);
        tvTip = inflate.findViewById(R.id.tv_devicce_tip);
        tvTip.setText(textTip);
        etInput = inflate.findViewById(R.id.et_device);
        spinnerDevice = inflate.findViewById(R.id.spinner_device);
        /**
         * 不显示Spinner  只显示扫描布局
         */
        if (!isContainSpinner) {
            spinnerDevice.setVisibility(GONE);
        }
        etInput.setOnFocusChangeListener((view, b) -> {
            if (b) {
                etInput.setFocusable(true);
                etInput.setFocusableInTouchMode(true);
                etInput.requestFocus();
                etInput.findFocus();
                etInput.setSelectAllOnFocus(true);
                Selection.selectAll(etInput.getText());
            }
        });
        this.addView(inflate);
    }

    /**
     * 设置Spinner的数据
     *
     * @param mDatas
     * @return
     */
    public DeviceView initDeviceData(List<InjectMoldBean.EqpmentsBean> mDatas) {
        /**
         * 设置原数据
         */
        this.mDiveces.clear();
        this.mDiveces.addAll(mDatas);

        ArrayList<String> mStrs=new ArrayList<>();
        for (int i = 0; i <mDiveces.size() ; i++) {
            mStrs.add(mDiveces.get(i).getDisplayText());
        }
        /**
         * 设置Spinner的数据
         */
        spinnerDevice.setItems(mStrs);
        spinnerDevice.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>)
                (view, position, id, item) -> {
                    etInput.setText(mDiveces.get(position).getValue());
                });

        return this;
    }
    /**
     * 设置Spinner的数据和Spinner 的监听器
     *
     * @param mDatas
     * @return
     */
    public DeviceView initDeviceData(List<InjectMoldBean.EqpmentsBean> mDatas, DeviceListener listener) {
        /**
         * 设置原数据
         */
        this.mDiveces.clear();
        this.mDiveces.addAll(mDatas);
        ArrayList<String> mStrs=new ArrayList<>();
        for (int i = 0; i <mDiveces.size() ; i++) {
            mStrs.add(mDiveces.get(i).getDisplayText());
        }
        /**
         * 设置Spinner的数据
         */
        spinnerDevice.setItems(mStrs);
        spinnerDevice.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>)
                (view, position, id, item) -> {
                    etInput.setText(mDiveces.get(position).getValue());
                    listener.deviceSelect(position);
                });

        return this;
    }

    /**
     * 设置 输入框  将输入框传进来
     *
     * @param listener         监听事件
     */
    public void setEdittextListener(final EdittextInputListener listener) {
        etInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == 6 || actionId == 0) {
                listener.hideInputSoftware();

                String content = etInput.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.showShort(inputTip);
                    return true;
                }
                /**
                 * 如果 isContainSpinner 则证明有Spinner
                 * 这个时候输入框输入/扫描后
                 * 需要去更改Spinner选择的位置和内容
                 */
                if (isContainSpinner) {
                    boolean isHaveMapStr = false;
                    for (int i = 0; i < mDiveces.size(); i++) {
                        if (content.equals(mDiveces.get(i).getValue())) {
                            /**
                             * 设置标识为true
                             */
                            isHaveMapStr = true;
                            /**
                             * 设置当前选择的位置和内容
                             */
                            spinnerDevice.setSelectedIndex(i);
                            spinnerDevice.setText(mDiveces.get(i).getDisplayText());
                        }
                    }
                    /**
                     * 如果没匹配则显示提示
                     */
                    if (!isHaveMapStr) {
                        ToastUtils.showShort(noMapStrTip);
                        etInput.setFocusable(true);
                        etInput.setFocusableInTouchMode(true);
                        etInput.requestFocus();
                        etInput.findFocus();
                        etInput.setSelectAllOnFocus(true);
                        Selection.selectAll(etInput.getText());
                        return true;
                    } else {
                        /**
                         * 验证通过，进行下一步
                         */
                        if (null != listener) {
                            listener.verticalSuccess(content);
                        }

                    }
                } else {
                    /**
                     * 验证通过，进行下一步
                     */
                    if (null != listener) {
                        listener.verticalSuccess(content);
                    }
                }
            }
            return false;
        });

    }

    /**
     * 设置扫描图标的点击事件
     */
    public DeviceView setImgScanListener(OnClickListener listener) {
        if (isScanByCamera) {
            ivDevice.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 获取当前Spinner选择的位置
     *
     * @return
     */
    public int getSpinnerSelectIndex() {
        return spinnerDevice.getSelectedIndex();
    }

    /**
     * 设置当前Spinner选择的位置
     *
     * @return
     */
    public DeviceView setSpinnerSelectIndex(int position) {
        spinnerDevice.setSelectedIndex(position);
        return this;
    }
    public DeviceView setEdittextContent(String content){
        etInput.setText(content);
        return  this;
    }
    /**
     * 设置当前Spinner的内容
     *
     * @return
     */
    public DeviceView setSpinnerText(String content) {
        spinnerDevice.setText(content);
        return this;
    }
    /**
     * 设置当前Spinner的内容
     *
     * @return
     */
    public DeviceView setSpinnerText(@StringRes int  content) {
        spinnerDevice.setText(content);
        return this;
    }
    /**
     * Spinner 设备选择接口
     */
    public interface DeviceListener {
        /**
         * 选择设备的方法
         *
         * @param position
         */
        void deviceSelect(int position);
    }

    /**
     * 所有的输入框的输入监听方法  验证全部通过则调用 verticalSuccess方法
     * 1、为了统一处理 输入错误设置内容选中
     * 2、为了监听回车键
     * 3、为了监听扫描键
     */
    public interface EdittextInputListener {
        /**
         * 返回成功结果
         *
         * @param result
         */
        void verticalSuccess(String result);
        /**
         * 隐藏输入框
         */
        void hideInputSoftware();
    }

}
