# dos文件转unix的gradle的插件

## 使用说明

windows系统执行下面的命令
~~~
.\gradlew uploadArchives

~~~
然后将 build/release下面生成的文件和目录放到项目的gradle/plugin 目录下面

在目标项目中引入这个插件

~~~
buildscript {
    repositories {
        maven{
            url "file:./gradle/plugin/"
        }
    }

    dependencies {
        classpath 'ant:dos2unix:1.0-SNAPSHOT'
    }
}

dos2Unix{
    sourceDir "$projectDir/scripts"
    targetDir "$buildDir/dist"
    suffixInclude ".sh"
}
~~~

然后执行下面命令即可

~~~
.\gradlew dos2Unix
~~~
