package com.nicos.percentageswithanimationcompose.utils.PercentageTextFormatter

/**
 * Formats a value as the label text shown by the percentage composables.
 *
 * @param value - The value to show, truncated to an Int
 * @param showPercentageSymbol - Appends % after the value when true
 * */
internal fun formatPercentageText(
    value: Float,
    showPercentageSymbol: Boolean,
): String {
    val number = value.toInt().toString()
    return if (showPercentageSymbol) "$number%" else number
}