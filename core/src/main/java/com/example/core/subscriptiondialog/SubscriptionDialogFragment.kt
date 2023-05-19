package com.example.core.subscriptiondialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.core.R
import com.example.core.databinding.DialogSubscriptionBinding


class SubscriptionDialogFragment() : DialogFragment() {

    private lateinit var binding: DialogSubscriptionBinding
    var okAction: (() -> Unit)? = null
    var cancelAction: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_corner)
        binding = DialogSubscriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogWork()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        cancelAction?.invoke()
    }

    private fun dialogWork() {
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding.buySub.setOnClickListener {
            okAction?.invoke()
            dismiss()
        }
        binding.noBuy.setOnClickListener {
            dismiss()
        }
    }
}




