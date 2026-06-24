package education.section10

object AnswerEx5:

  sealed trait Tree[A]
  case class Leaf[A](value: A)                        extends Tree[A]   // 葉（値を 1 つ持つ）
  case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]   // 枝（左右に木を持つ）

  def treeQuestion(tree: Tree[Int]): Int =
    tree match
      case Leaf(v)      => v
      case Branch(l, r) => treeQuestion(l) + treeQuestion(r)



  def main(args: Array[String]): Unit =
    println(treeQuestion(Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))))
