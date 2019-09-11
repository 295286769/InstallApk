package com.sharkgulf.checkandinstallapk.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.sharkgulf.checkandinstallapk.R
import com.sharkgulf.checkandinstallapk.databinding.CheckapkPopupDialogBinding
import java.lang.ref.WeakReference
import com.sharkgulf.checkandinstallapk.inteface.DialogButtonLeftInterface
import com.sharkgulf.checkandinstallapk.inteface.DialogButtonRightInterface
import com.sharkgulf.checkandinstallapk.utils.ScreenUtil

/**
 * 下载更新弹框
 */
class DialogUtil : Dialog,View.OnClickListener {

    private val TAG = "PopupDialog"
    private var popupDialogBinding: CheckapkPopupDialogBinding? = null
    private lateinit var dialogBuider: DialogBuider
    /**
     * 使用弱引用防止内存泄漏
     */
    private  lateinit var mContext: WeakReference<Context>
    private var width: Int = 0
    private constructor(context: Context, dialogBuider: DialogBuider) : super(context, R.style.checkapk_Dialog_Common) {
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popupDialogBinding = mContext.get()?.let { DataBindingUtil.inflate(LayoutInflater.from(mContext.get()), R.layout.checkapk_popup_dialog, null, false) }
            popupDialogBinding?.let { it.setDialogBuider(dialogBuider) }
        popupDialogBinding?.let { it.setOnClickListener(this) }
            popupDialogBinding?.let { setContentView( popupDialogBinding?.root)}
            dialogBuider?.let { setCancelable(dialogBuider?.getCancelable())}
            dialogBuider?.let { setCanceledOnTouchOutside(dialogBuider?.getCanceledOnTouchOutside()) }
    }
    override fun onClick(view: View?) {
        when(view?.getId()){
            R.id.common_dialog_cancel_tv ->{
                dissDialog()
                dialogBuider?.let { it.getOnLeftButtonInterface()?.onComfireClick()}
            }
            R.id.common_dialog_confirm_tv ->{
                dissDialog()
                dialogBuider?.let { it.getOnButtonRightInterface()?.onComfireClick() }
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
       public class DialogBuider {
            private lateinit var context: Context
            //标题
            private var title = ""
            //左边按钮文字
            private var leftBtnText = ""
            //右边按钮文字
            private var rightBtnText = ""
            //文本文字
            private var contentText = ""
            //是否可以取消
            private var cancelable = true
            //点击外部区域取消
            private var canceledOnTouchOutside = true
            //左边按钮监听
            public var dialogButtonLefttInterface:DialogButtonLeftInterface?= null
            //右边按钮监听
            private var dialogButtonRightInterface:DialogButtonRightInterface?= null
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

            fun setLeftBtnText(leftBtnText: String,dialogButtonInterface: DialogButtonLeftInterface): DialogBuider {
                this.leftBtnText = leftBtnText
                this.dialogButtonLefttInterface=dialogButtonInterface
                return this
            }

            fun getLeftBtnText(): String {
                return leftBtnText
            }

            fun setRightBtnText(rightBtnText: String,dialogButtonInterface: DialogButtonRightInterface): DialogBuider {
                this.rightBtnText = rightBtnText
                this.dialogButtonRightInterface=dialogButtonInterface
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
            fun getOnLeftButtonInterface():DialogButtonLeftInterface?=dialogButtonLefttInterface
            fun getOnButtonRightInterface():DialogButtonRightInterface?=dialogButtonRightInterface

            fun buider(): DialogUtil {
                return DialogUtil(context, this)

            }


        }
    }


}