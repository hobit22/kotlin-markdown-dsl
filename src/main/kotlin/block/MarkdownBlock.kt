package com.rojobit22.block

/**
 * This interface defines a parser for markdown blocks.
 * To support a new markdown block type, implement this interface first.
 *
 * @author rojojun
 */
interface MarkdownBlock {

    /**
     * Appends the given text to the markdown content.
     *
     * @param text the text to append to the content
     * @return the updated StringBuilder containing the markdown content
     */
    fun content(text: String): StringBuilder

    /**
     * Appends the given text to the markdown content.
     *
     * This executes the provided lambda and appends its returned string
     * to the current markdown content
     *
     * @param block a lambda that generates the text to append
     * @return the updated StringBuilder containing the markdown content
     */
    fun content(block: () -> String): StringBuilder
}