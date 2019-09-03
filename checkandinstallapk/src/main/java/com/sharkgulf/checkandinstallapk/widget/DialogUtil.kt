package com.sharkgulf.checkandinstallapk.widget

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.sharkgulf.checkandinstallapk.R
import com.sharkgulf.checkandinstallapk.databinding.PopupDialogBinding
import kotlinx.android.synthetic.main.popup_dialog.*


class DialogUtil: AlertDialog {
    private val TAG = "PopupDialog"
    constructor(context: Context, cancelable:Boolean,  canceledOnTouchOutside:Boolean):super(context, R.style.Dialog_Common){
        var popupDialogBinding:PopupDialogBinding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.popup_dialog,null,false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}