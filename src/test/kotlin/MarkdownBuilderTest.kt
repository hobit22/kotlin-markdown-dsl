import com.rojobit22.markdown
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
                item("1번")
                item("2번")
            }

            code("kotlin") {
                text("println(\"Hello World\")")
            }
        }

        Assertions.assertEquals(expected, result)
    }
}
