package education.section3

class PokemonSkill(
  val name: String,//技名
  val pronunciation: String,//よみがな
  val kind: String,//種別
  val power: Int//威力
)

class Pokemon(
  val name: String,//ポケモン名
  val pronunciation: String,//よみがな
  val hpMax: Int,//最大 HP
  val hp: Int,//現在 HP
  val skill: Seq[PokemonSkill]//覚えている技
)


class Trainer(
  val name: String,//トレーナー名
  val holdsPokemon: Seq[Pokemon]//手持ちポケモン
)

object AnswerEx3_1:
  val trainers: Seq[Trainer] = Seq(
    Trainer("サトシ",Seq(
      Pokemon("ピカチュウ", "ぴかちゅう", 35, 35, Seq(
        PokemonSkill("電光石火", "でんこうせっか", "攻撃",40),
        PokemonSkill("自己再生", "じこさいせい", "回復", 50)
      ) ),
      Pokemon("リザードン", "りざーどん", 78, 78, Seq(
          PokemonSkill("火炎放射", "かえんほうしゃ", "攻撃",90),
          PokemonSkill("破壊光線", "はかいこうせん", "攻撃", 150)
      ) ),
      Pokemon("カビゴン", "かびごん", 160, 160,Seq(
          PokemonSkill("地震", "じしん", "攻撃",100),
          PokemonSkill("回復", "かいふく", "回復", 60)
    ) ) ) ),
    Trainer("カスミ", Seq(
      Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
          PokemonSkill("水鉄砲", "みずでっぽう", "攻撃",45),
          PokemonSkill("甲羅休め", "こうらやすめ", "回復", 50)
      ) ),
      Pokemon("フシギバナ", "ふしぎばな", 80, 80, Seq(
          PokemonSkill("草結び", "くさむすび", "攻撃",65),
          PokemonSkill("光合成", "こうごうせい", "回復", 70)
    ) ) ) ),
    Trainer("タケシ", Seq(
      Pokemon("ゼニガメ", "ぜにがめ", 44, 44, Seq(
          PokemonSkill("水鉄砲", "みずでっぽう	", "攻撃",45),
          PokemonSkill("甲羅休め", "こうらやすめ", "回復", 40)
      ) ),
      Pokemon("イワーク", "いわーく", 55, 55, Seq(
        PokemonSkill("岩石封じ", "がんせきふうじ", "攻撃",60),
        PokemonSkill("地割れ", "じわれ", "攻撃", 120)
      ) ),
      Pokemon("イシツブテ", "いしつぶて", 40, 40, Seq(
        PokemonSkill("落石", "らくせき", "攻撃",50),
        PokemonSkill("治療", "ちりょう", "回復", 30)
  ) ) ) ) )

  def main(args: Array[String]): Unit =
    //showAllSkills(trainers)

  //def showAllSkills(trainers: Seq[Trainer]): Unit =
    //println(  
      //trainers.
      //flatMap(t => t.holdsPokemon).//トレーナーのポケモンを出す
      //flatMap(p => p.skill).//ポケモンの技を出す
      //sortBy(s => s.pronunciation).//読み仮名順に入れ替え
      //map(s => s.name).//技だけにする
      //distinct.//重複を消す
      //mkString("\n"))
  showHierarchy(trainers)


  def showHierarchy(trainers: Seq[Trainer]): Unit =
    trainers
      .sortBy(_.name)
      .foreach { trainer =>

        println(trainer.name)

        trainer.holdsPokemon
          .sortBy(_.pronunciation)
          .foreach { pokemon =>

            println(s"  ${pokemon.name} (HP${pokemon.hpMax})")

            pokemon.skill
              .sortBy(-_.power)
              .foreach(v => println(s"    ${v.name} (${v.kind} / 威力${v.power})"))
          }
      }
    
