package block

import com.rojobit22.block.MarkdownBlock

internal class ReferenceBlock(
    private val content: StringBuilder,
) : MarkdownBlock {
    override fun content(text: String): StringBuilder = content.appendLine("> $text")

    override fun content(block: () -> String): StringBuilder = content.appendLine("> ${block()}")
}