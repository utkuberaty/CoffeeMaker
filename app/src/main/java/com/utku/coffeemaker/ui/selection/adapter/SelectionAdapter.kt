package com.utku.coffeemaker.ui.selection.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.utku.base.util.TAG
import com.utku.coffeemaker.R
import com.utku.coffeemaker.databinding.SelectionItemBinding
import com.utku.coffeemaker.px
import com.utku.data.entities.Extras
import com.utku.data.entities.SelectedExtra
import com.utku.data.entities.Sizes
import com.utku.data.entities.Types


class SelectionAdapter<T>(
    private val selections: List<T>,
    private val onSelectedSelection: ((T) -> Unit)? = null,
    private val onExtraEditSelected: (() -> Unit)? = null
) : RecyclerView.Adapter<SelectionAdapter<T>.ViewHolder>() {

    var selectedExtras: MutableMap<String, SelectedExtra> = mutableMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(SelectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(selections[position])
    }

    override fun getItemCount(): Int = selections.size

    inner class ViewHolder(private val viewBinding: SelectionItemBinding) : RecyclerView.ViewHolder(
        viewBinding.root
    ) {

        internal fun bind(selection: T) {
            when (selection) {
                is Types -> setTypesItems(selection)
                is Sizes -> setSizeItems(selection)
                is Extras -> setExtraItems(selection)
                is SelectedExtra -> setSelectedExtra(selection)
            }
            if (onSelectedSelection != null)
                viewBinding.root.setOnClickListener {
                    onSelectedSelection.invoke(selection)
                }

        }

        private fun setTypesItems(type: Types) {
            val typeImageRes = when (type.name) {
                "Espresso" -> R.drawable.espresso
                "Cappuccino" -> R.drawable.cappuchino
                else -> R.drawable.espresso
            }
            viewBinding.apply {
                selectionImageView.setImageResource(typeImageRes)
                selectionNameTextView.text = type.name
            }
        }

        private fun setSizeItems(size: Sizes) {
            viewBinding.apply {
                val typeImageRes = when (size.name.lowercase()) {
                    "Large" -> R.drawable.large
                    "Venti" -> R.drawable.medium
                    "Tall" -> R.drawable.small
                    else -> R.drawable.small
                }
                viewBinding.apply {
                    selectionImageView.setImageResource(typeImageRes)
                    selectionNameTextView.text = size.name
                }
            }
        }

        private fun setExtraItems(extra: Extras) {
            val typeImageRes = if (extra.name.lowercase().split(" ").any {
                    it.lowercase() == "milk"
                }) R.drawable.milk
            else R.drawable.sugar_cubes

            viewBinding.apply {
                selectionExpandableLayout.apply {
                    isExpanded = true
                    visibility = View.VISIBLE
                }
                divider.visibility = View.VISIBLE
                selectionNameTextView.text = extra.name
                selectionImageView.setImageResource(typeImageRes)
                selectionRadioGroup.setOnCheckedChangeListener { _, id ->
                    selectedExtras[extra.name] = SelectedExtra(extra.name, extra.subselections[id])
                    Log.i(TAG, "selectedExtras are -> $selectedExtras")
                }
                extra.subselections.forEachIndexed { index, subselections ->
                    selectionRadioGroup.addView(
                        createRadioButton(index).apply {
                            if (index == 0) isChecked = true
                            text = subselections.name
                        }
                    )
                }
            }
        }

        private fun setSelectedExtra(selectedExtra: SelectedExtra) {
            val typeImageRes = if (selectedExtra.extraName.lowercase().split(" ").any {
                    it.lowercase() == "milk"
                }) R.drawable.milk
            else R.drawable.sugar_cubes

            viewBinding.apply {
                divider.visibility = View.VISIBLE
                selectionExpandableLayout.apply {
                    isExpanded = true
                    visibility = View.VISIBLE
                }
                selectionNameTextView.text = selectedExtra.extraName
                selectionImageView.setImageResource(typeImageRes)
                selectionRadioGroup.addView(
                    createRadioButton().apply {
                        isChecked = true
                        text = selectedExtra.subselections.name
                    }
                )
                selectionEditTextView.apply {
                    visibility = View.VISIBLE
                    setOnClickListener { onExtraEditSelected?.invoke() }
                }
            }
        }

        private fun createRadioButton(radioButtonId: Int = 0): RadioButton {
            val layoutInflater = viewBinding.root.context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as LayoutInflater
            return (layoutInflater.inflate(
                R.layout.radio_button,
                null
            ) as RadioButton).apply {
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(left, top, right, 10.px)
                }
                id = radioButtonId
            }
        }

    }

}