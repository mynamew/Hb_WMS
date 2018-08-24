package com.jzk.hebi_wms.utils;

import android.content.Context;

import com.jzk.hebi_wms.R;
import com.jzk.hebi_wms.view.MyDialog;

/**
 * 通用的Dialog
 *
 * @author jzk
 * create at: 2018/8/24 15:06
 */
public class CommonDialogUtils {

    public static MyDialog showErrorTipDialog(Context context, String title, String content) {
        MyDialog myDialog = new MyDialog(context, R.layout.dialog_error_tip)
                .setTextViewContent(R.id.tv_title, title)
                .setTextViewContent(R.id.tv_content, content)
                .setImageViewListener(R.id.iv_close, dialog -> dialog.dismiss())
                .setButtonListener(R.id.btn_cancel, null, dialog -> dialog.dismiss());
        myDialog.show();
        return myDialog;
    }
}
