package com.rojobit22.block

internal class HeaderBlock(
    private val content: StringBuilder
) : MarkdownBlock {
    override fun item(text: String) = content.appendLine(text)

    fun newLine() = content.appendLine()
}
