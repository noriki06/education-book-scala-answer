# 個人リポジトリへの格納（git 操作）

このリポジトリは研修を開始するための **雛形（テンプレート）** です。
実際の実装は、雛形を clone し、リモートを **自分の個人リポジトリ** へ付け替えてから進めます。
（この雛形リポジトリへ直接コミット／push はしないでください）

```bash
# 1. 雛形をローカルに clone します
$ git clone git@github.com:hybrid-tech-dev/education-book-scala-answer.git
$ cd education-book-scala-answer

# 2. GitHub 等で、自分用の空のリポジトリを作成しておきます
#    例: git@github.com:<自分のアカウント>/education-book-scala-answer.git

# 3. リモート(origin) を雛形から自分のリポジトリへ付け替えます
$ git remote set-url origin git@github.com:<自分のアカウント>/education-book-scala-answer.git

# 付け替わったことを確認します（雛形ではなく自分のリポジトリになっていればOK）
$ git remote -v
origin  git@github.com:<自分のアカウント>/education-book-scala-answer.git (fetch)
origin  git@github.com:<自分のアカウント>/education-book-scala-answer.git (push)

# 4. 自分のリポジトリへ push します
$ git push -u origin main
```

> 補足: 手順 3 で origin を付け替えることで、雛形リポジトリ（`hybrid-tech-dev/education-book-scala-answer`）への誤 push を防げます。
> 以降は、自分のリポジトリへ通常どおり `git add` / `git commit` / `git push` して研修を進めてください。

セットアップが完了したら、研修の進め方は [README.md](../README.md) を参照してください。
