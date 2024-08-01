<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="id" label="药品ID" width="70">
    </el-table-column>
    <el-table-column prop="name" label="药品名称">
    </el-table-column>
    <el-table-column prop="price" label="交易总金额">
    </el-table-column>
    <el-table-column prop="saleNumbers" label="交易总数">
    </el-table-column>
  </el-table>
</template>
  
  <script>
  export default {
    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '习大大',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '习大大',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '习大大',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '习大大',
          address: '上海市普陀区金沙江路 1516 弄'
        }]
      }
    }
  }
  </script>