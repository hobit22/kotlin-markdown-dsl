package com.rojobit22.block

internal class CodeBlock(
    private val content: StringBuilder
) : MarkdownBlock {
    override fun item(text: String) = content.appendLine(text.trim())

    fun content(text: String) = content.appendLine(text.trim())
}
