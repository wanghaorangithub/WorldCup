package com.example.a24078.worldcup.support.dataStructure.linkedlist

/**
 * Created by haoran on 2018/9/27.
 */
class LinkedList<T> {

    private var root: Node<T>? = null//根节点
    private var size = 0

    init {
        root = null
    }

    //添加节点
    fun add(data: T) {
        if (root == null) {
            root = Node(data)
            size++
        } else {
            root!!.addNode(data)
            size++
        }
    }

    //删除节点
    fun del(data: T) {
        if (root == null) return
        if (root?.data === data) {
            root = root!!.next
            size--
        } else {
            root?.delNode(data)
            size--
        }
    }

    fun clear() {
        root = null
        size = 0
    }

    //在尾部添加一个链表
    fun addAll(linkedList: LinkedList<T>) {
        if (root == null) {
            root = linkedList.root
            size += linkedList.size()
        } else {
            linkedList.root?.let { root?.addNode(it) }
            size += linkedList.size()
        }
    }

    //输出所有节点
    fun print() {
        if (root != null) {
            println(root!!.data!!.toString() + "->")
            root!!.printNode()
            println()
        }
    }

    //查找节点是否存在
    fun find(data: T): Boolean {
        if (root == null) return false
        return if (root!!.data === data) {
            true
        } else {
            root!!.findNode(data)
        }
    }


    //修改节点
    fun update(oldData: T, newData: T): Boolean? {
        if (root == null) return false
        if (root?.data === oldData) {
            root?.data = newData
            return true
        } else {
            return root?.updateNode(oldData, newData)
        }
    }

    //根据位置获取节点
    operator fun get(position: Int): T? {
        if (position <= size) {
            var tmp = root
            for (i in 0 until position)
                tmp = tmp!!.next
            return tmp?.data
        } else
            return null
    }

    //返回size
    fun size(): Int {
        return size
    }

    operator fun set(position: Int, value: T?) {
        if (position <= size) {
            var tmp = root
            for (i in 0 until position)
                tmp = tmp!!.next
            tmp?.data = value
        }
    }
}