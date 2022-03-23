package com.utku.coffeemaker.ui.selection.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import com.utku.base.ui.BaseFragment
import com.utku.coffeemaker.databinding.OverviewFragmentBinding
import com.utku.coffeemaker.ui.root_activity.RootViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OverviewFragment : BaseFragment<OverviewFragmentBinding>({
    OverviewFragmentBinding.inflate(it)
}) {

    override val viewModel: RootViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().onBackPressedDispatcher.addCallback { }
        setOverviewRecyclerView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setOverviewRecyclerView() {
        /*viewBinding.styleRecyclerView.apply {
            adapter = SelectionAdapter(
                listOf(
                    Types(name = "Espresso"),
                    Types(name = "Cappuccino"),
                    Types(name = "Ristretto")
                )
            ) {
                Log.i(TAG, "selected -> $it")
            }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }*/
    }
}