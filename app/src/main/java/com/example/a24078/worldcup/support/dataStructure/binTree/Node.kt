package com.example.a24078.worldcup.support.dataStructure.binTree

/**
 * Created by haoran on 2018/11/4.
 */
class Node<T>(var data: T?) {
    var rightChild: Node<T>? = Node(null)
    var leftChild: Node<T>? = null
    var parents: Node<T>? = null

    fun addNode(data: T) {
        if (this.data == null) {
            this.data = data
            return
        } else {
            if (this.leftChild == null) {
                val node = Node(data)
                node.parents = this
                this.leftChild = node
                return
            } else {
                if (this.rightChild == null) {
                    val node = Node(data)
                    node.parents = this
                    this.rightChild = node
                    return
                } else {
                    leftChild?.addNode(data)
                    rightChild?.addNode(data)
                }
            }
        }
    }


}
