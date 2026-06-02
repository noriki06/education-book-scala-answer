
# 研修の進め方

> このリポジトリは研修を開始するための **雛形（テンプレート）** です。
> 実際の実装は、このプロジェクトを **自分の個人リポジトリに格納して** 進めてください。
> （この雛形リポジトリへ直接コミット／push はしないでください）
>
> 個人リポジトリへの格納手順（git 操作）は [docs/01_GIT.md](docs/01_GIT.md) を参照してください。

## 環境
 - Scala 3.6.4
 - sbt 1.12.11
 - JDK 21 (Amazon Corretto)

> JDK / sbt のインストールなど、環境構築がまだの場合は [docs/02_SETUP.md](docs/02_SETUP.md) を参照して環境を整えてください。

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

`compile` を実行すると、`src` 配下に格納されたソースコード（`.scala`）を全てコンパイルします。

```
[SBT] net.ixias@education-book-answer:[main] > compile
[info] compiling 1 Scala source to .../target/scala-3.6.4/classes ...
[info] done compiling
[success] Total time: 1 s, completed 2026/06/03 08:15:01
```

### コンパイルとは？（PHP / Ruby との違い）
「コンパイル」とは、人が書いたソースコードを、コンピュータ（ここでは JVM = Java仮想マシン）が実行できる形式（バイトコード, `.class` ファイル）へ **実行前にまとめて変換する** 工程のことです。Scala はこのコンパイルが必要な「**コンパイル型**」かつ「**静的型付け**」の言語です。

PHP や Ruby は「**インタプリタ型**（スクリプト言語）」で、事前のコンパイルは不要です。プログラムを動かしたその場で、ソースコードを上から順番に解釈しながら実行します。

両者の一番大きな違いは **エラーに気づくタイミング** です。

| | Scala（コンパイル型・静的型付け） | PHP / Ruby（インタプリタ型・動的型付け） |
| --- | --- | --- |
| 型の不一致・存在しないメソッド呼び出し | **コンパイル時**（=実行する前）に検出される | 実行してその行に到達して初めてエラーになることが多い |
| 実行前の準備 | `.class` への変換（コンパイル）が必要 | 不要。ソースをそのまま実行できる |
| メリット | バグを早期に発見でき、IDE の補完・リファクタリングが強力 | 手軽に書いてすぐ動かせる |

> 補足: 本プロジェクトは `build.sbt` の `scalacOptions` に `-Xfatal-warnings` を設定しているため、**警告（warning）もエラー扱い** になりコンパイルが止まります。警告が出たら必ず解消してください。

## 実行方法

### runMain（実行するクラスを明示して動かす）
`runMain <完全修飾名>` で、実行したいエントリポイント（`main` / `@main`）を **明示的に指定** して実行します。研修では回答ファイルが増えてエントリポイントが複数になるため、基本はこちらを使います。

```
[SBT] net.ixias@education-book-answer:[main] > runMain education.section1.sample
[info] running (fork) education.section1.sample
[info] [OK] processing....
[success] Total time: 1 s, completed 2026/06/03 08:15:01
```

Scala 3 では、ソース内の `@main def sample` のように書いたメソッド名がそのままエントリポイント名（`education.section1.sample`）になります。

### run（エントリポイントを選んで動かす）
`run` は、プロジェクト内のエントリポイントを実行します。エントリポイントが 1 つだけなら自動的にそれを実行し、**複数ある場合は一覧から番号で選択** するよう促されます。

```
[SBT] net.ixias@education-book-answer:[main] > run

Multiple main classes detected. Select one to run:
 [1] education.section1.sample
 [2] education.section2.answer1

Enter number: 1
[info] running (fork) education.section1.sample
[info] [OK] processing....
```

> 補足: ログに出ている `running (fork)` は、`build.sbt` の `Compile / run / fork := true` により **sbt 本体とは別の JVM プロセス** でアプリを起動していることを表します。`compile` を忘れていても、`run` / `runMain` は実行前に自動で必要なコンパイルを行います。

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
