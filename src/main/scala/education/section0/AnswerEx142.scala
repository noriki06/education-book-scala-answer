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
      .sortBy(skill => skill.yomigana)
      .distinct
      .map(skill => skill.name)
      .foreach(println)

  def showHierarchy(trainers: Seq[Trainer]): Unit =
    trainers
      .sortBy(_.name)
      .foreach { trainer =>
        println(trainer.name)
        trainer.pokemons.sortBy(_.yomigana)
        .zipWithIndex
        .foreach{ pokemons match
          case (_, index) if index >= pokemon.size =>
            println(s"└─${pokemon.name} (HP${pokemon.hpMax})")
          case (_, index) else                     =>
            println(s"├─${pokemon.name} (HP${pokemon.hpMax})")
          pokemon.skills.sortBy(-_.power)
          .foreach { skill =>
            println(s"    ${skill.name} (${skill.kind} / 威力${skill.power})")
          }
        }
      }






  def main(args: Array[String]): Unit =
    showHierarchy(trainers)
