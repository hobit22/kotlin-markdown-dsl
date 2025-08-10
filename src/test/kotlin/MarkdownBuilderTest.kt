import com.rojobit22.builder.markdown
import com.rojobit22.builder.toFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MarkdownBuilderTest {
    @Test
    fun markdownCreateTest() {
        val expected = """
            # 테스트
            ## 중제목
            * 1번
            * 2번
            ```kotlin
            println("Hello World")
            ```
            
        """.trimIndent()

        val result = markdown {
            header1("테스트")
            header2("중제목")

            bulletList {
                content("1번")
                content("2번")
            }

            code("kotlin") {
                text("println(\"Hello World\")")
            }
        }

        Assertions.assertEquals(expected, result)
    }

    @Test
    fun headerUsage() {
        val result = markdown {
            header1("Header") {
                content("simple content")
                content {
                    val users = listOf(
                        User("Alice", 25, "Developer"),
                        User("Bob", 30, "Designer"),
                        User("Charlie", 28, "Manager")
                    )

                    val report = StringBuilder()
                    report.appendLine("=== User report ===")

                    users.groupBy { it.role }
                        .forEach { (role, userList) ->
                            report.appendLine("**$role** (${userList.size})")
                            userList.forEach { user ->
                                report.appendLine("  - ${user.name} (${user.age} years old)")
                            }
                            report.appendLine()
                        }

                    report.toString()
                }
            }
        }

        println(result)
    }

    @Test
    fun toFileTest() {
        // Given
        val testFilePath = "test_markdown.md"
        val charset = Charsets.UTF_8
        val expectedContent = """
        # 테스트 문서
        ## 서브 헤더
        * 첫 번째 항목
        * 두 번째 항목
        ```kotlin
        fun hello() {
            println("Hello, World!")
        }
        ```
        
    """.trimIndent()

        try {
            // When
            toFile(testFilePath, charset) {
                header1("테스트 문서")
                header2("서브 헤더")

                bulletList {
                    content("첫 번째 항목")
                    content("두 번째 항목")
                }

                code("kotlin") {
                    text("fun hello() {")
                    text("    println(\"Hello, World!\")")
                    text("}")
                }
            }

            // Then
            val file = File(testFilePath)
            assertTrue(file.exists(), "파일이 생성되어야 함")

            val actualContent = file.readText(charset)
            assertEquals(expectedContent, actualContent, "파일 내용이 일치해야 함")

        } finally {
            // 테스트 후 정리
            File(testFilePath).deleteOnExit()
        }
    }
}

data class User(val name: String, val age: Int, val role: String)
