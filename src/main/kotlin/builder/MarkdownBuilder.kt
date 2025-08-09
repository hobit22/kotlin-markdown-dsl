package com.rojobit22.builder

import com.rojobit22.annotation.MarkdownDsl
import com.rojobit22.block.BulletListBlock
import com.rojobit22.block.CodeBlock
import com.rojobit22.block.HeaderBlock
import com.rojobit22.block.MarkdownBlock
import java.io.File
import java.nio.charset.Charset

@MarkdownDsl
class MarkdownBuilder(
    private val charset: Charset = Charsets.UTF_8,
) {
    private val content = StringBuilder()

    fun header1(text: String, block: MarkdownBlock.() -> Unit = {}) {
        content.appendLine("# $text")
        HeaderBlock(content).apply(block)
    }

    fun header2(text: String, block: MarkdownBlock.() -> Unit = {}) {
        content.appendLine("## $text")
        HeaderBlock(content).apply(block)
    }

    fun header3(text: String, block: MarkdownBlock.() -> Unit = {}) {
        content.appendLine("### $text")
        HeaderBlock(content).apply(block)
    }

    fun bulletList(block: MarkdownBlock.() -> Unit) {
        BulletListBlock(content).apply(block)
    }

    fun code(language: String = "", block: MarkdownBlock.() -> Unit) {
        content.appendLine("```$language")
        CodeBlock(content).apply(block)
        content.appendLine("```")
    }

    fun text(content: String) {
        this.content.appendLine(content)
    }

    fun newLine() {
        content.appendLine()
    }

    internal fun build() = content.toString()
}

fun markdown(block: MarkdownBuilder.() -> Unit): String = MarkdownBuilder().apply(block).build()

fun toFile(filePath: String, charset: Charset, block: MarkdownBuilder.() -> Unit) = File(filePath).writeText(markdown { block() }, charset)
