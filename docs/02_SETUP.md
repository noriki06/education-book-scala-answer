# 環境構築

本研修は次の環境を前提としています。

 - JDK 21 (Amazon Corretto)
 - sbt 1.12.11
 - Scala 3.6.4

Scala 本体は sbt がプロジェクト設定（`build.sbt` の `scalaVersion := "3.6.4"`）に従って自動的に取得するため、個別にインストールする必要はありません。以下では JDK と sbt のセットアップ手順を説明します。

# 1. JDK (Amazon Corretto) のインストール
元々は Oracle JDK / Oracle OpenJDK が広く使われていたのですが、2019 年 4 月 16 日、Java を提供しているオラクル社からの発表（ライセンスの変更など）によって大混乱が起きました。(詳しいことは調べてください)

Amazon Corretto は Amazon が提供する LTS(Long term support) 付きの無償の OpenJDK のディストリビューションです。OpenJDK との大きな違いは、Amazon によるパフォーマンスの向上とセキュリティの問題に対する不具合の修正などを含むサポート（本研修で利用する Corretto 21 の場合は 2030 年 10 月まで）を用意している LTS 付きのものであるということです。

下記リンクから **Amazon Corretto 21** をダウンロードしましょう。
 - https://aws.amazon.com/jp/corretto/

mac にインストールされている JDK のバージョンを次のコマンドで取得します。
```bash
$ /usr/libexec/java_home -V
```

(例) 複数のバージョンがインストールされていると次のようになります。
```bash
$ /usr/libexec/java_home -V
Matching Java Virtual Machines (2):
    21.0.7 (arm64) "Amazon.com Inc." - "Amazon Corretto 21" /Library/Java/JavaVirtualMachines/amazon-corretto-21.jdk/Contents/Home
    11.0.23 (arm64) "Amazon.com Inc." - "Amazon Corretto 11" /Library/Java/JavaVirtualMachines/amazon-corretto-11.jdk/Contents/Home
/Library/Java/JavaVirtualMachines/amazon-corretto-21.jdk/Contents/Home
```

本研修で利用する Corretto 21 に切り替えてみます。
```bash
$ /usr/libexec/java_home -v 21
 /Library/Java/JavaVirtualMachines/amazon-corretto-21.jdk/Contents/Home
```

この JAVA_HOME の PATH を通してあげることで、バージョンの切り替えが可能です。
```~/.zshrc``` に次の設定を追加
```bash
export JAVA_HOME=`/usr/libexec/java_home -v 21`
PATH=$JAVA_HOME/bin:$PATH
```

設定を反映します。
```bash
$ source ~/.zshrc
```

バージョンが切り替わっているかを確認します。
```bash
$ java -version
openjdk version "21.0.7" 2025-04-15 LTS
OpenJDK Runtime Environment Corretto-21.0.7.6.1 (build 21.0.7+6-LTS)
OpenJDK 64-Bit Server VM Corretto-21.0.7.6.1 (build 21.0.7+6-LTS, mixed mode, sharing)
```

# 2. sbt のインストール
Scala の開発に必要な sbt をインストールします。
```bash
$ brew install sbt
```

インストールできたか確認します。
```bash
$ sbt --version
sbt script version: 1.12.11
```

セットアップが完了したら、研修の進め方は [README.md](../README.md) を参照してください。
