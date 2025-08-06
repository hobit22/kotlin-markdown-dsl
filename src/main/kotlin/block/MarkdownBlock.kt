package com.rojobit22.block

interface MarkdownBlock {
    fun content(text: String): StringBuilder

    fun content(block: () -> String): StringBuilder
}