package com.rojobit22.block

internal class HeaderBlock(
    private val content: StringBuilder
) : MarkdownBlock {
    override fun content(text: String) = content.appendLine(text)

    override fun content(block: () -> String): StringBuilder = content.appendLine(block())
}
