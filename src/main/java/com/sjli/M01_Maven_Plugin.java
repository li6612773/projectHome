package com.sjli;

public class M01_Maven_Plugin {
    public static void main(String[] args) {

        /*
        使用插件
        阅读: 1000525
        我们在前面介绍了Maven的lifecycle，phase和goal：使用Maven构建项目就是执行lifecycle，执行到指定的phase为止。每个phase会执行自己默认的一个或多个goal。goal是最小任务单元。

        我们以compile这个phase为例，如果执行：

        mvn compile
        Maven将执行compile这个phase，这个phase会调用compiler插件执行关联的compiler:compile这个goal。

        实际上，执行每个phase，都是通过某个插件（plugin）来执行的，Maven本身其实并不知道如何执行compile，它只是负责找到对应的compiler插件，然后执行默认的compiler:compile这个goal来完成编译。

        所以，使用Maven，实际上就是配置好需要使用的插件，然后通过phase调用它们。

        Maven已经内置了一些常用的标准插件：

        插件名称	对应执行的phase
        clean	clean
        compiler	compile
        surefire	test
        jar	package
        如果标准插件无法满足需求，我们还可以使用自定义插件。使用自定义插件的时候，需要声明。例如，使用maven-shade-plugin可以创建一个可执行的jar，要使用这个插件，需要在pom.xml中声明它：

        <project>
            ...
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-shade-plugin</artifactId>
                        <version>3.2.1</version>
                        <executions>
                            <execution>
                                <phase>package</phase>
                                <goals>
                                    <goal>shade</goal>
                                </goals>
                                <configuration>
                                    ...
                                </configuration>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </project>
        自定义插件往往需要一些配置，例如，maven-shade-plugin需要指定Java程序的入口，它的配置是：

        <configuration>
            <transformers>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                    <mainClass>com.itranswarp.learnjava.Main</mainClass>
                </transformer>
            </transformers>
        </configuration>
        注意，Maven自带的标准插件例如compiler是无需声明的，只有引入其它的插件才需要声明。

        下面列举了一些常用的插件：

        maven-shade-plugin：打包所有依赖包并生成可执行jar；
        cobertura-maven-plugin：生成单元测试覆盖率报告；
        findbugs-maven-plugin：对Java源码进行静态分析以找出潜在问题。
         */
    }
}
