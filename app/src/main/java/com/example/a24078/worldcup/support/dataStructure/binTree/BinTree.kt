package com.example.a24078.worldcup.support.dataStructure.binTree

import android.annotation.SuppressLint
import android.util.Log
import java.util.*

/**
 * Created by haoran on 2018/11/4.
 */
class BinTree<T> {
    var root: Node<T>? = Node(null)

    fun add(data: T) {
        root?.addNode(data)
    }

    fun clear() {
        root = null
    }

    @SuppressLint("LogNotTimber")
    fun levelOrderTraversal(): LinkedList<Node<T>>? {
        val result=LinkedList<Node<T>>()
        if (root == null) {
            Log.e("error","empty tree")
            return null
        }
        val queue = ArrayDeque<Node<T>>()
        queue.add(root)
        while (!queue.isEmpty()) {
            val node = queue.remove()
            result.add(node)
            System.out.print(node.data)
            if (node.leftChild != null) {
                queue.add(node.leftChild)
            }
            if (node.rightChild != null) {
                queue.add(node.rightChild)
            }
        }
        return result
    }

}
