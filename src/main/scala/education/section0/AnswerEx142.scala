package education.section0

object AnswerEx142:
  // トレーナクラス
  case class Trainer(
    name: String,           // トレーナー名
    pokemons: Seq[Pokemon]  // 手持ちポケモン
  )
  // ポケモンクラス
  case class Pokemon(
    name: String,      // ポケモン名
    yomigana: String,  // よみがな
    hp: Int,           // 現在HP
    hpMax: Int,        // 最大HP
    skills: Seq[Skill] // 覚えている技
  )
  // スキルクラス
  case class Skill(
    name: String,     // 技名
    yomigana: String, // よみがな
    kind: String,     // 種別
    power: Int        // 威力
  )

  val trainers: Seq[Trainer]=
    Seq(
      Trainer("サトシ", Seq(
        Pokemon("ピカチュウ", "ぴかちゅう", 35, 35, Seq(
          Skill("電光石火", "でんこうせっか", "攻撃", 40),
          Skill("自己再生", "じこさいせい", "回復", 50)
        )),
        Pokemon("リザードン", "りざーどん", 78 , 78, Seq(
          Skill("火炎放射", "かえんほうしゃ", "攻撃", 90),
          Skill("破壊光線", "はかいこうせん", "攻撃", 150)
        )),
        Pokemon("カビゴン", "かびごん", 160	, 160, Seq(
          Skill("地震", "じしん", "攻撃", 100),
          Skill("回復", "かいふく", "回復", 60)
        ))
      )),
      Trainer("カスミ", Seq(
        Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
          Skill("水鉄砲", "みずでっぽう", "攻撃", 45),
          Skill("甲羅休め", "こうらやすめ", "回復", 40)
        )),
        Pokemon("フシギバナ", "ふしぎばな", 80 , 80, Seq(
          Skill("草結び", "くさむすび", "攻撃", 65),
          Skill("光合成", "こうごうせい", "回復", 70)
        ))
      )),
      Trainer("タケシ", Seq(
        Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
          Skill("電光石火", "でんこうせっか", "攻撃", 40),
          Skill("自己再生", "じこさいせい", "回復", 50)
        )),
        Pokemon("イワーク", "いわーく", 55 , 55, Seq(
          Skill("岩石封じ", "がんせきふうじ", "攻撃", 60),
          Skill("地割れ", "じわれ", "攻撃", 120)
        )),
        Pokemon("イシツブテ", "いしつぶて", 40 , 40, Seq(
          Skill("落石", "らくせき", "攻撃", 50),
          Skill("治療", "ちりょう", "回復", 30)
        ))
      ))
    )

  def showAllSkills(trainers: Seq[Trainer]): Unit =
    val skills =
      for {
        trainer <- trainers
        pokemon <- trainer.pokemons
        skill   <- pokemon.skills
      } yield skill

    skills
      .sortBy(_.yomigana)
      .distinct
      .map(_.name)
      .foreach(println)

  def showHierarchy(trainers: Seq[Trainer]): Unit =
    trainers.sortBy(_.name).foreach { trainer => // トレーナー名でソート
      println(trainer.name) // トレーナ名を出力
      val sortPokemon = trainer.pokemons.sortBy(_.yomigana) // 読み仮名でソートしたポケモンの並び
      sortPokemon.zipWithIndex.foreach { (pokemon, pIdx) => // index付けしたポケモンの並び
        val isPokemonLast = pIdx == sortPokemon.size - 1 // indexがラストかどうかの判定
        val poketree       = if isPokemonLast then "└─ " else "├─ " //
        val pokeend   = if isPokemonLast then "   " else "│  "
        println(s"$poketree${pokemon.name} (HP${pokemon.hpMax})")
        val sortSkill = pokemon.skills.sortBy(-_.power)
        sortSkill.zipWithIndex.foreach { (skill, sIdx) =>
          val isSkillLast = sIdx == sortSkill.size - 1
          val skitree     = if isSkillLast then "└─ " else "├─ "
          println(s"$pokeend$skitree${skill.name} (${skill.kind} / 威力${skill.power})")
        }
      }
    }

  def randomDamage(trainer: Trainer): Trainer =
    val damage = MATH_RANDOM.nextInt(100) // ランダムなダメージ（0〜99 ）
    val pokeIndex = MATH_RANDOM.nextInt(trainer.pokemons.size)
    val target = trainer.pokemons(pokeIndex) // ポケモンを１体ランダムに選ぶ
    trainer.copy(pokemons =
      trainer
        .pokemons
        .updated(pokeIndex, target.copy(
          hp =
            math.max(0, target.hp - damage))
        )
    )

  def main(args: Array[String]): Unit =
    println(Trainer("サトシ", Seq(
        Pokemon("ピカチュウ", "ぴかちゅう", 35, 35, Seq(
          Skill("電光石火", "でんこうせっか", "攻撃", 40),
          Skill("自己再生", "じこさいせい", "回復", 50)
        )),
        Pokemon("リザードン", "りざーどん", 78 , 78, Seq(
          Skill("火炎放射", "かえんほうしゃ", "攻撃", 90),
          Skill("破壊光線", "はかいこうせん", "攻撃", 150)
        )),
        Pokemon("カビゴン", "かびごん", 160	, 160, Seq(
          Skill("地震", "じしん", "攻撃", 100),
          Skill("回復", "かいふく", "回復", 60)
        )))))

  val MATH_RANDOM = new scala.util.Random(256)
