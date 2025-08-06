package com.rojobit22.block

internal class CodeBlock(
    private val content: StringBuilder
) : MarkdownBlock {
    override fun content(text: String) = content.appendLine(text.trim())

    override fun content(block: () -> String): StringBuilder = content.appendLine(block())
}
