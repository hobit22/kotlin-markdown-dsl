import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses

@AnalyzeClasses(packages = ["com.rojobit22"])
class ArchitectureTest {

    @ArchTest
    fun `block 구현체들의 접근성 제한`(classes: JavaClasses) {
        classes().that().resideInAPackage("..block..")
            .and().implement(com.rojobit22.block.MarkdownBlock::class.java)
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage(
                "com.rojobit22",
                "com.rojobit22.block..",
                "com.rojobit22.builder"
            )
            .because("Block 구현체들은 라이브러리 외부에서 직접 사용되면 안됨")
            .check(classes)
    }

    @ArchTest
    fun `block, annotation 의존성 제한`(classes: JavaClasses) {
        noClasses().that().resideInAPackage("..block..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("..annotation..", "..builder..")
            .check(classes)

        noClasses().that().resideInAPackage("..annotation..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("..block..", "..builder..")
            .check(classes)
    }

    @ArchTest
    fun `오직 builder 레이어만 다른 레이어를 의존할 수 있다`(classes: JavaClasses) {
        classes().that().resideInAPackage("..builder..")
            .should().onlyHaveDependentClassesThat()
            .resideInAnyPackage(
                "..block..",
                "..annotation..",
                "kotlin..",
                "java..",
                "..builder.."
            )
            .check(classes)
    }
}
