
# 研修の進め方

## 環境
 - Scala 3.6.4
 - sbt 1.12.11
 - JDK 21 (Amazon Corretto)

> JDK / sbt のインストールなど、環境構築がまだの場合は [docs/01_SETUP.md](docs/01_SETUP.md) を参照して環境を整えてください。

## sbt の起動
```
$ sbt
  [info] welcome to sbt 1.12.11 (Amazon.com Inc. Java 21.0.7)
  [info] loading settings for project education-book-answer-build from plugin.sbt ...
  [info] loading project definition from /Volumes/dev/git-dev/EDU/education-book-answer/project
  [info] loading settings for project root from build.sbt ...
  [info] set current project to education-book-answer (in build file:/Volumes/dev/git-dev/EDU/education-book-answer/)
  [info] sbt server started at local:///Users/yoshinobu.kinugasa/.sbt/1.0/server/767e416845b11ce566d4/sock
  [info] started sbt server
  [SBT] net.ixias@education-book-answer:[main] >
```
のような感じで立ち上がります。ここで Java が 21系を利用していることを確認してください

## コンパイル方法
 - src 配下に格納されたソースコードを全てコンパイルします

```
[SBT] net.ixias@education-book-answer:[main] > compile
[success] Total time: 1 s, completed 2026/06/03 08:15:01
```

## 実行方法
 - 実行したい指定のクラスを指定します

```
[SBT] net.ixias@education-book-answer:[main] > runMain education.section1.sample
[info] running (fork) education.section1.sample
[info] [OK] processing....
[success] Total time: 1 s, completed 2026/06/03 08:15:01
```

## ディレクトリ構造

```
 + src/main/scala/education
   + section1
     + Answer1.scala (回答1) <-- ファイル名はなんでも良い
     + Answer2.scala (回答2)
     + Answer3.scala (回答3)
   + section2
     + Answer1.scala (回答1)
     + Answer2.scala (回答2)
     + Answer3.scala (回答3)
   + section3
     + ...
   + section4
     + ...
```
のような構造で作って行ってください
