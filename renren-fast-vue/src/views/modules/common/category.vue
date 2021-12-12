<template>
  <el-tree :data="menus"
           :props="defaultProps"
           node-key="catId"
           ref="menuTree"
           @node-click="nodeClick"
  >

  </el-tree>
</template>

<script>
export default {
  name: 'category',
  data () {
    return {
      // 所有在展开状态的节点key
      expandedKeys: [],
      // 数据列表
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  created () {
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
        this.menus = data.data
      })
    },
    nodeClick(data,node,component){
      this.$emit("node-click",data,node,component);
    }
  },
}
</script>

<style scoped>

</style>
