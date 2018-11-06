package com.example.a24078.worldcup.support.dataStructure.linkedlist

/**
 * Created by haoran on 2018/9/27.
 */
class Node<T>(var data: T?) {
  var next: Node<T>? = null//把当前类型作为属性

  //添加节点
  fun addNode(data: T) {
    if (this.next == null) {
      this.next = Node(data)
    } else {
      this.next!!.addNode(data)
    }
  }

  fun addNode(node: Node<T>) {
    if (this.next == null) {
      this.next = node
    } else {
      this.next!!.addNode(node)
    }
  }

  //删除节点
  fun delNode(data: T) {
    if (this.next != null) {
      this.next = this.next!!.next
    } else {
      this.next!!.delNode(data)
    }
  }

  //输出所有节点
  fun printNode() {
    if (this.next != null) {
      println(this.next!!.data!!.toString() + "->")
      this.next!!.printNode()
    }

  }

  //查找节点
  fun findNode(data: T): Boolean {
    return if (this.next != null) {
      if (this.next!!.data === data) {
        true
      } else {
        this.next!!.findNode(data)
      }
    } else false
  }

  //修改节点
  fun updateNode(oldData: T, newData: T): Boolean? {
    if (this.next != null) {
      if (this.next?.data === oldData) {
        this.next?.data = newData
        return true
      } else {
        return this.next?.updateNode(oldData, newData)
      }
    }
    return false
  }
}
