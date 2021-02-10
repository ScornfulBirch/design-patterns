package structural

import structural.TreeFactory.getTreeType
import java.awt.Color
import java.awt.Graphics
import java.util.*
import javax.swing.JFrame
import kotlin.math.floor

/**
 * Flyweight
 *
 * Flyweight is a structural design pattern that lets you fit more objects into the available amount
 * of RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.
 */

class Tree(
    private val x: Int,
    private val y: Int,
    private val type: TreeType,
) {

    fun draw(g: Graphics) {
        type.draw(g, x, y)
    }
}

class TreeType(
    private val name: String,
    private val color: Color,
    private val otherTreeData: String,
) {

    fun draw(g: Graphics, x: Int, y: Int) = with(g) {
        color = Color.BLACK
        fillRect(x - 1, y, 3, 5)
        color = color
        fillOval(x - 5, y - 10, 10, 10)
    }
}

object TreeFactory {

    private val treeTypes: MutableMap<String, TreeType> = mutableMapOf()

    fun getTreeType(name: String, color: Color, otherTreeData: String): TreeType {
        var result = treeTypes[name]
        if (result == null) {
            result = TreeType(name, color, otherTreeData)
            treeTypes[name] = result
        }
        return result
    }
}

class Forest : JFrame() {
    private val trees: MutableList<Tree> = ArrayList()

    fun plantTree(x: Int, y: Int, name: String, color: Color, otherTreeData: String) {
        val type = getTreeType(name, color, otherTreeData)
        val tree = Tree(x, y, type)
        trees.add(tree)
    }

    override fun paint(graphics: Graphics) {
        for (tree in trees) {
            tree.draw(graphics)
        }
    }
}

const val CANVAS_SIZE = 680
const val TREES_TO_DRAW = 1000000F
const val TREE_TYPES = 2F

fun main() {
    val treesCount = floor(TREES_TO_DRAW / TREE_TYPES).toInt()
    Forest().apply {
        repeat((0..treesCount).count()) {
            plantTree(
                random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                "Summer Oak", Color.GREEN, "Oak texture stub"
            )
            plantTree(
                random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub"
            )
        }
        setSize(CANVAS_SIZE, CANVAS_SIZE)
        isVisible = true
    }

    println("$TREES_TO_DRAW trees drawn")
    println("---------------------")
    println("Memory usage:")
    println("Tree size (8 bytes) * $TREES_TO_DRAW")
    println("+ TreeTypes size (~30 bytes) * $TREE_TYPES")
    println("---------------------")
    println(
        "Total: " + (TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024 +
                "MB (instead of " + TREES_TO_DRAW * 38 / 1024 / 1024 + "MB)"
    )


}

private fun random(min: Int, max: Int): Int {
    return min + (Math.random() * (max - min + 1)).toInt()
}
