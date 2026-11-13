package com.nicos.percentageswithanimationcompose.utils.percentageTextFormatter

/**
 * Formats the label text shown by the percentage composables.
 *
 * @param currentPercentage - The current (animated) value
 * @param maxPercentage - The maximum value; used to work out the real percentage
 * @param showPercentageSymbol - When true, shows currentPercentage / maxPercentage as a percentage with a % sign
 * */
internal fun formatPercentageText(
    currentPercentage: Float,
    maxPercentage: Float,
    showPercentageSymbol: Boolean,
): String {
    // Without the symbol, keep the old behavior: the raw value, truncated
    if (!showPercentageSymbol) return currentPercentage.toInt().toString()
    // With the symbol, show the true share of max, so 100 of 200 reads "50%"
    val percent = if (maxPercentage > 0f) currentPercentage / maxPercentage * 100f else 0f
    return "${percent.toInt()}%"
}