package com.utku.coffeemaker.ui.root_activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.utku.base.ui.BaseActivity
import com.utku.coffeemaker.R
import com.utku.coffeemaker.databinding.RootActivityBinding
import com.utku.coffeemaker.ui.progress_bar.ProgressBarDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : BaseActivity<RootActivityBinding>({ RootActivityBinding.inflate(it) }) {

    private val progressBarDialog = ProgressBarDialog()

    override val viewModel: RootViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.showProgress.observe(this) {
            if (it) {
                lifecycleScope.runCatching {
                    progressBarDialog.show(supportFragmentManager, null)
                }
            } else {
                lifecycleScope.runCatching {
                    progressBarDialog.dismiss()
                }
            }
        }

        viewModel.showError.observe(this) {
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.ops)
                .setMessage(it.ifEmpty { getString(R.string.problem_with_coffee_maker) })
                .setPositiveButton(R.string.thanks) { dialog, _ ->
                    dialog.dismiss()
                }.show()
        }
    }
}