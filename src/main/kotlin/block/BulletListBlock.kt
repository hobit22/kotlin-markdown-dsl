package com.rojobit22.block

class BulletListBlock(
    private val content: StringBuilder
): MarkdownBlock {
    override fun item(text: String) = content.appendLine("* $text")

    fun item(block: () -> String) = content.appendLine("* ${block()}")
}
