import com.rojobit22.builder.markdown
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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
}

data class User(val name: String, val age: Int, val role: String)
