package com.sharkgulf.checkandinstallapk.widget

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sharkgulf.checkandinstallapk.R
import kotlinx.android.synthetic.main.popup_dialog.*
import java.lang.ref.WeakReference
import com.huangshang.checkandinstallapk.inteface.CheckSelfPermissionCall
import com.sharkgulf.checkandinstallapk.databinding.PopupDialogBinding
import com.sharkgulf.checkandinstallapk.utils.ScreenUtil
import com.sharkgulf.checkandinstallapk.utils.StartActivityUtil

/**
 * 下载更新弹框
 */
class DialogUtil : Dialog,View.OnClickListener {

    private val TAG = "PopupDialog"
    private var popupDialogBinding: PopupDialogBinding? = null
    private lateinit var dialogBuider: DialogBuider
    private  lateinit var mContext: WeakReference<Context>
    private var width: Int = 0
    //检查权限接口
    private var checkSelfPermissionCall: CheckSelfPermissionCall?=null

    private constructor(context: Context, dialogBuider: DialogBuider) : super(context, R.style.Dialog_Common) {
        mContext=WeakReference<Context>(context)
        this.dialogBuider = dialogBuider
        if(mContext.get()!=null){
            var deviceWidth = ScreenUtil.getScreenWidth(mContext.get())
            try {
                width =  (deviceWidth * 0.7f).toInt()
            } catch (e: Exception) {
            }
        }

    }
    fun setCheckSelfPermissionCall(checkSelfPermissionCall: CheckSelfPermissionCall){
        this.checkSelfPermissionCall=checkSelfPermissionCall
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popupDialogBinding = mContext.get()?.let { DataBindingUtil.inflate(LayoutInflater.from(mContext.get()), R.layout.popup_dialog, null, false) }
            popupDialogBinding?.let { it.setDialogBuider(dialogBuider) }
            popupDialogBinding?.let { setContentView( popupDialogBinding?.root)}
            dialogBuider?.let { setCancelable(dialogBuider?.getCancelable())}
            dialogBuider?.let { setCanceledOnTouchOutside(dialogBuider?.getCanceledOnTouchOutside()) }
            common_dialog_cancel_tv.setOnClickListener(this)
            common_dialog_confirm_tv.setOnClickListener(this)
    }

    /**
     * 启动service下载apk
     */
    fun updateApk(){
        StartActivityUtil.startUpdateApkService(mContext.get())
    }
    override fun onClick(view: View?) {
        when(view?.getId()){
            R.id.common_dialog_cancel_tv ->{
                dissDialog()

            }
            R.id.common_dialog_confirm_tv ->{
                dissDialog()
                checkSelfPermissionCall?.onCheck()
            }
        }
    }
    fun showDialog(){
        if(!isShowing){
            show()
        }
    }
    fun dissDialog(){
        if(isShowing){
            dismiss()
        }
    }

    companion object {
        /**
         * 弹框百题内容对象
         */
        class DialogBuider {
            private lateinit var context: Context
            private var title = ""
            private var leftBtnText = ""
            private var rightBtnText = ""
            private var contentText = ""
            private var cancelable = true
            private var canceledOnTouchOutside = true
            constructor(context: Context){
                this.context=context
            }
            fun setTitle(title: String): DialogBuider {
                this.title = title
                return this
            }

            public fun getTitle(): String {
                return title
            }

            fun setLeftBtnText(leftBtnText: String): DialogBuider {
                this.leftBtnText = leftBtnText
                return this
            }

            fun getLeftBtnText(): String {
                return leftBtnText
            }

            fun setRightBtnText(rightBtnText: String): DialogBuider {
                this.rightBtnText = rightBtnText
                return this
            }

            fun getRightBtnText(): String {
                return rightBtnText
            }

            fun setContentText(contentText: String): DialogBuider {
                this.contentText = contentText
                return this
            }

            fun getContentText(): String {
                return contentText
            }

            fun setCancelable(cancelable: Boolean): DialogBuider {
                this.cancelable = cancelable
                return this
            }
            fun getCancelable(): Boolean {
                return cancelable
            }
            fun setCanceledOnTouchOutside(): DialogBuider {
                this.canceledOnTouchOutside = canceledOnTouchOutside
                return this
            }
            fun getCanceledOnTouchOutside(): Boolean {
                return canceledOnTouchOutside
            }

            fun buider(): DialogUtil {
                return DialogUtil(context, this)

            }


        }
    }


}