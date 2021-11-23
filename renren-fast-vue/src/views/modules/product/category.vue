<template>
  <div class="mod-config">
    <el-form :inline="true" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-button
            type="primary"
            @click="append({catId: 0})"
        >新增一级目录
        </el-button>
        <el-button
            type="danger"
            @click="remove()"
            :disabled="ids.length <= 0"
        >批量删除
        </el-button>
      </el-form-item>
    </el-form>

    <el-tree :data="dataList" :props="defaultProps" :expand-on-click-node="false" node-key="catId" show-checkbox
             style="width: 30%" @check="selectionChange" :default-expanded-keys="expandedKeys" draggable
             :allow-drop="allowDrop" @node-drop="handleDrop">
       <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
              v-if="node.level <= 2"
              type="text"
              size="mini"
              @click="() => append(data)"
          >添加
          </el-button>
          <el-button
              type="text"
              size="mini"
              @click="() => updateHandle(data)"
          >修改
          </el-button>
          <el-button
              v-if="node.childNodes.length === 0"
              type="text"
              size="mini"
              @click="() => remove(data)"
          >删除
          </el-button>
        </span>
      </span>
    </el-tree>

    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="completeAddOrUpdate"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './category-add-or-update'

export default {
  data () {
    return {
      // 所有在展开状态的节点key
      expandedKeys: [],
      // 数据列表
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      // 遮罩
      dataListLoading: false,
      // 选中id
      ids: [],
      updateData: {},
      parentCids: [],
      maxLevel: 0,
      updateNodes: [],
      addOrUpdateVisible: false,
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  components: {
    AddOrUpdate
  },
  activated () {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        this.dataList = data.data
        this.dataListLoading = false
      })
    },
    /**
     * 选中事件
     */
    selectionChange (currNode, checkedNodes) {
      this.ids = checkedNodes.checkedKeys
      this.names = checkedNodes.checkedNodes.map(node => node.name)
      this.parentCids = checkedNodes.checkedNodes.map(node => node.parentCid)
    },
    // 每页数
    sizeChangeHandle (val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 新增 / 修改
    updateHandle (data) {
      this.updateData = data
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(data, data.catId)
      })
    },
    completeAddOrUpdate () {
      this.getDataList()
      this.expandedKeys = [this.updateData.catId]
    },
    /**
     * 添加节点方法
     */
    append (data) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(data)
      })
    },
    remove (obj) {
      let ids
      let name
      let parentCid
      if (obj === undefined) {
        ids = this.ids
        name = this.names
        parentCid = this.parentCids
      } else {
        ids = [obj.catId]
        name = obj.name
        parentCid = [obj.parentCid]
      }
      this.$confirm(`确定对[${name}]进行[${ids ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.expandedKeys = parentCid
                this.getDataList()
              }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      })
    },
    /**
     * 拖动
     * @param draggingNode
     * @param dropNode
     * @param type
     * @returns {boolean}
     */
    allowDrop (draggingNode, dropNode, type) {

      this.countNodeLevel(draggingNode.data)

      let deep = this.maxLevel - draggingNode.data.catLevel + 1

      if (type === 'inner') {
        return deep + dropNode.level <= 3
      } else {
        return deep + dropNode.parent.level <= 3
      }
    },
    /**
     * 计算节点深度
     * @param node
     */
    countNodeLevel (node) {
      if (node.children != null && node.children.length > 0) {

        for (let i = 0; i < node.children.length; i++) {
          if (node.children[i].catLevel > this.maxLevel) {
            this.maxLevel = node.children[i].catLevel
          }
          this.countNodeLevel(node.children[i])
        }

      }
    },
    /**
     * 拖拽完成
     * @param draggingNode 正在拖动节点
     * @param dropNode 托东
     * @param dropType
     * @param ev
     */
    handleDrop (draggingNode, dropNode, dropType, ev) {
      this.updateNodes = []
      let pCid = 0
      let siblings = null
      if (dropType === 'before' || dropType === 'after') {
        pCid = dropNode.parent.data.catId === undefined ? 0 : dropNode.parent.data.catId
        siblings = dropNode.parent.childNodes
      } else {
        pCid = dropNode.data.catId
        siblings = dropNode.childNodes

      }

      for (let i = 0; i < siblings.length; i++) {
        //如果遍历的是当前节点
        if (siblings[i].data.catId === draggingNode.data.catId) {
          let catLevel = draggingNode.level
          // 当前层级发生改变
          if (siblings[i].level !== draggingNode.level) {
            // 修改层级
            catLevel = siblings[i].level
            // 修改当前节点的子层级
            this.updateChildNodeLevel(siblings[i])
          }

          this.updateNodes.push({
            catId: siblings[i].data.catId,
            sort: i,
            parentCid: pCid,
            catLevel: catLevel
          })
        } else {
          this.updateNodes.push({
            catId: siblings[i].data.catId,
            sort: i
          })
        }
      }

      // TODO: 2021/11/24 上午 12:03 Ashai 剩余拖拽后接口的请求
      console.log(this.updateNodes)
    },
    /**
     * 修改子节点层级
     * @param node
     */
    updateChildNodeLevel (node) {
      if (node.childNodes.length > 0) {
        node.childNodes.forEach(childNode => {
          let cNode = childNode.data
          this.updateNodes.push({catId: cNode.catId, catLevel: childNode.level})
          this.updateChildNodeLevel(childNode)
        })
      }

    }

  }
}
</script>
<style lang="scss" scoped>

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}


</style>
