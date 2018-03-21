package thomastodon.io.parameterizedintegrationtests;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

class BirdTest {

    @TestTemplate
    @ExtendWith(MyTestTemplateInvocationContextProvider.class)
    void testTemplate(String bird) {
        System.out.println(bird);
    }

    static class MyTestTemplateInvocationContextProvider implements TestTemplateInvocationContextProvider {
        @Override
        public boolean supportsTestTemplate(ExtensionContext context) {
            return true;
        }

        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return Stream.of(invocationContext("chicken"), invocationContext("duck"));
        }

        private TestTemplateInvocationContext invocationContext(String bird) {
            return new TestTemplateInvocationContext() {
                @Override
                public String getDisplayName(int invocationIndex) {
                    return bird;
                }

                @Override
                public List<Extension> getAdditionalExtensions() {
                    return asList(preProcessor(), postProcessor(), parameterResolver());
                }

                private ParameterResolver parameterResolver() {
                    return new ParameterResolver() {
                        @Override
                        public boolean supportsParameter(
                            ParameterContext parameterContext,
                            ExtensionContext extensionContext
                        ) {
                            return parameterContext.getParameter().getType().equals(String.class);
                        }

                        @Override
                        public Object resolveParameter(
                            ParameterContext parameterContext,
                            ExtensionContext extensionContext
                        ) {
                            return bird;
                        }
                    };
                }

                private BeforeTestExecutionCallback preProcessor() {
                    return extensionContext -> System.out.println("Pre-process parameter: " + bird);
                }

                private AfterTestExecutionCallback postProcessor() {
                    return extensionContext -> System.out.println("Post-process parameter: " + bird);
                }
            };
        }
    }
}