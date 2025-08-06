package com.rojobit22.block

internal class BulletListBlock(
    private val content: StringBuilder
): MarkdownBlock {
    override fun content(text: String) = content.appendLine("* $text")

    override fun content(block: () -> String) = content.appendLine("* ${block()}")
}
