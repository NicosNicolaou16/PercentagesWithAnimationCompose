package com.nicos.percentageswithanimationcompose.utils.percentageTextFormatter

/**
 * Formats the label text shown by the percentage composables.
 *
 * @param value - The current (animated) value
 * @param maxValue - The maximum value; used to work out the real percentage
 * @param showPercentageSymbol - When true, shows value / maxValue as a percentage with a % sign
 * */
internal fun formatPercentageText(
    value: Float,
    maxValue: Float,
    showPercentageSymbol: Boolean,
): String {
    // Without the symbol, keep the old behavior: the raw value, truncated
    if (!showPercentageSymbol) return value.toInt().toString()
    // With the symbol, show the true share of max, so 100 of 200 reads "50%"
    val percent = if (maxValue > 0f) value / maxValue * 100f else 0f
    return "${percent.toInt()}%"
}